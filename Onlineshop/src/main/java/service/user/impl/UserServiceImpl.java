package service.user.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import service.base.impl.BaseServiceImpl;
import service.user.UserService;
import util.JSONUtil;
import util.LuceneUtil;
import util.SessionUtil;
import util.UUIDUtil;
import VO.CartVO;
import VO.ComplicatedOrderVO;
import VO.FeedbackVO;
import VO.LoginVO;
import VO.OrderItemVO;
import VO.OrderListVO;
import VO.ReviewVO;
import VO.SimpleOrderVO;
import VO.SliderInfoVO;
import constant.CookieConst;
import constant.FilePathConst;
import constant.JSONConst;
import constant.SessionConst;
import constant.SpringConfigConst;
import dao.base.BaseDao;
import entity.Cart;
import entity.CartItem;
import entity.Category;
import entity.Item;
import entity.Order;
import entity.OrderItem;
import entity.Review;
import entity.User;
import factory.MapperFactory;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private MapperFactory mapperFactory;

	private ApplicationContext applicationContext;

	public UserServiceImpl() {
		applicationContext = new ClassPathXmlApplicationContext(SpringConfigConst.APPLICATION,SpringConfigConst.APPLICATION_DAO);
		super.setBaseDao((BaseDao) applicationContext.getBean("userMapper"));
	}

	@Override
	public String publishItem(Item item,  MultipartHttpServletRequest request, HttpServletResponse response) {
		int result;
		try {
			Map<String, MultipartFile> fileMap = request.getFileMap();
			Set<Entry<String, MultipartFile>> fileEntrySet = fileMap.entrySet();
			LOGGER.info("file size:"+fileMap.keySet().size());
			String filePath = "";
			String img = "";
			StringBuilder subImgBuilder = new StringBuilder();
			for (Iterator<Entry<String, MultipartFile>> iterator = fileEntrySet.iterator(); iterator.hasNext();) {
				Entry<String, MultipartFile> entry = (Entry<String, MultipartFile>) iterator.next();
				String fileName = entry.getValue().getOriginalFilename();
				String fieldName = entry.getValue().getName();
				if(fieldName.equals("mainImg")) {
					img = fileName;
				}else {
					subImgBuilder.append(FilePathConst.ITEM_IMG_DIR+fileName);
					if(iterator.hasNext()) subImgBuilder.append(";");
				}
				MultipartFile file = entry.getValue();
				filePath = FilePathConst.ITEM_IMG_DIR + fileName;
				file.transferTo(new File(filePath));
				LOGGER.info(fileName+" transfered completed");
			}
			item.setId(mapperFactory.getItemMapper().genId());
			item.setImg(FilePathConst.ITEM_IMG_DIR + img);
			item.setSub_img(subImgBuilder.toString());
			item.setPublish_time(new Date());
			item.setUser_id(SessionUtil.getUserId(request));
			LOGGER.info(item.toString());
			result = mapperFactory.getItemMapper().save(item);
			LOGGER.info("save:"+result);
			if(result > 0) {
				LuceneUtil lucene = new LuceneUtil();
				lucene.createIndex(item);
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			return JSONUtil.createCustom(JSONConst.MESSAGE, JSONConst.FAILURE);
		}
		return JSONUtil.createCustom(JSONConst.MESSAGE, JSONConst.FAILURE);
	}

	@Override
	public User findByName(String username) {
		return mapperFactory.getUserMapper().findByName(username);
	}

	@Override
	public String feedback(FeedbackVO feedback, String userId) {
		Review review = new Review();
		Integer orderItemId = feedback.getOrderItem_id();
		OrderItem orderItem = mapperFactory.getOrderItemMapper().findById(orderItemId);
		mapperFactory.getOrderItemMapper().setStatus(orderItemId, 4);
		review.setOrder_id(orderItem.getOrder_id());
		review.setItem_id(orderItem.getItem_id());
		review.setUser_id(userId);
		review.setFeedback(feedback.getFeedback());
		review.setContent(feedback.getContent());
		if(mapperFactory.getReviewMapper().save(review) > 0) {
			return JSONUtil.success;
		}
		return JSONUtil.failure;
	}

	@Override
	public String pay(Integer orderItemId) {
		return mapperFactory.getOrderItemMapper().setStatus(orderItemId, 1) > 0 ? JSONUtil.success : JSONUtil.failure;
	}

	@Override
	public String delivery(Integer orderItemId) {
		return mapperFactory.getOrderItemMapper().setStatus(orderItemId, 2) > 0 ? JSONUtil.success : JSONUtil.failure;
	}

	@Override
	public String receive(Integer orderItemId) {
		return mapperFactory.getOrderItemMapper().setStatus(orderItemId, 3) > 0 ? JSONUtil.success : JSONUtil.failure;
	}

	@Override
	public void removeFromCart(List<Integer> cartItemIds) {
		mapperFactory.getCartItemMapper().removeBatch(cartItemIds);
	}

	@Override
	public int genOrderByCart(ComplicatedOrderVO orderInfo, String customerId) {
		List<CartItem> cartItems = mapperFactory.getCartItemMapper().getByIds(orderInfo.getCartItemIds());
		for (CartItem cartItem : cartItems) {
			if(mapperFactory.getItemMapper().getRepository(cartItem.getItem_id()) < cartItem.getQuantity()) {
				return 0;
			}
		}
		//gen Order
		Order order = new Order();
		order.setId(UUIDUtil.genUUID());
		order.setCtm_id(customerId);
		order.setGen_time(new Date());
		order.setAddress(orderInfo.getAddress());
		order.setReceiverName(orderInfo.getReceiverName());
		order.setTelephone(orderInfo.getTelephone());
		mapperFactory.getOrderMapper().save(order);
		for (CartItem cartItem : cartItems) {
			//gen order item by the latest generated order and a cartItem
			OrderItem orderItem = new OrderItem();
			Integer itemId = cartItem.getItem_id();
			int quantity = cartItem.getQuantity();
			orderItem.setItem_id(itemId);
			orderItem.setOrder_id(order.getId());
			orderItem.setQuantity(quantity);
			mapperFactory.getItemMapper().updateRepository(itemId, quantity);
			mapperFactory.getItemMapper().updateVolume(itemId, quantity);
			mapperFactory.getOrderItemMapper().save(orderItem);
		}
		return 1;
	}

	@Override
	public String genOrder(SimpleOrderVO orderVO, String customerId) {
		String result = null;
		try {
			//先判断库存是否>1
			int repo = mapperFactory.getItemMapper().getRepository(orderVO.getItemId());
			if(repo >= 1) {
				Order order = new Order();
				order.setId(UUIDUtil.genUUID());
				order.setCtm_id(customerId);
				order.setGen_time(new Date());
				order.setReceiverName(orderVO.getReceiverName());
				order.setTelephone(orderVO.getTelephone());
				order.setAddress(orderVO.getAddress());
				mapperFactory.getOrderMapper().save(order);
				//数量默认为1
				OrderItem orderitem = new OrderItem();
				Integer itemId = orderVO.getItemId();
				orderitem.setOrder_id(order.getId());
				orderitem.setItem_id(itemId);
				mapperFactory.getItemMapper().updateRepository(itemId, 1);
				mapperFactory.getItemMapper().updateVolume(itemId, 1);
				mapperFactory.getOrderItemMapper().save(orderitem);
				result = JSONUtil.success;
			}else if(repo == 0) {
				result = JSONUtil.createCustom(JSONConst.MESSAGE, JSONConst.REPOSITORY_NOT_ENOUGH);
			}
		} catch (Exception e) {
			result = JSONUtil.failure;
		}
		return result;
	}

	@Override
	public String modifyCartItem(CartItem cartItemInfo, String userId) {
		List<CartItem> rows = mapperFactory.getCartItemMapper().getAllByCart(mapperFactory.getCartMapper().getByUser(userId).getId());
		for (CartItem cartItem : rows) {
			if(cartItem.getItem_id() == cartItemInfo.getItem_id()) {
				cartItem.setQuantity(cartItemInfo.getQuantity());
				cartItemInfo = cartItem;
			}
		}
		if(mapperFactory.getCartItemMapper().update(cartItemInfo) > 0) {
			return JSONUtil.success;
		}else{
			return JSONUtil.failure;
		}
	}

	@Override
	public String putIntoCart(Integer itemId, String userId) {
		//判断该用户是否有购物车
		if(mapperFactory.getCartMapper().getByUser(userId) == null) {
			Cart cart = new Cart();
			cart.setUser_id(userId);
			mapperFactory.getCartMapper().save(cart);
		}
		//判断该商品是否已存在
		//BUG:没有登录验证、item不匹配
		List<CartItem> cartItems = mapperFactory.getCartItemMapper().getAllByCart(mapperFactory.getCartMapper().getByUser(userId).getId());
		for (CartItem cartItem : cartItems) {
			LOGGER.info("cartItem id:"+cartItem.getId()+",cartItem item id:"+cartItem.getItem_id()+",item id:"+itemId);
			if(cartItem.getItem_id().intValue() == itemId.intValue()) {
				//已存在，数量+1
				LOGGER.info("cartItem exists");
				if(mapperFactory.getCartItemMapper().updateQuantity(cartItem.getId(), cartItem.getQuantity() + 1) > 0) {
					return JSONUtil.success;
				}else{
					return JSONUtil.failure;
				}
			}
		}
		//不存在，新建
		LOGGER.info("cartItem doesn't exist");
		CartItem cartItem = new CartItem();
		cartItem.setCart_id(mapperFactory.getCartMapper().getByUser(userId).getId());
		cartItem.setItem_id(itemId);
		if(mapperFactory.getCartItemMapper().save(cartItem) > 0){
			return JSONUtil.success;
		}else{
			return JSONUtil.failure;
		}
	}

	@Override
	public String removeItem(Integer itemId) {
		try {
			LuceneUtil luceneUtil = new LuceneUtil();
			luceneUtil.delIndex(LuceneUtil.FIELD_ITEM_NAME, mapperFactory.getItemMapper().findById(itemId).getName());
			mapperFactory.getItemMapper().delete(itemId);
		} catch (IOException e) {
			e.printStackTrace();
			return JSONUtil.failure;
		}
		return JSONUtil.success;
	}

	@Override
	public List<CartVO> checkMyCart(String userId) {
		// TODO Auto-generated method stub
		Cart cart = mapperFactory.getCartMapper().getByUser(userId);
		if(cart != null) {
			List<CartItem> cartItems = mapperFactory.getCartItemMapper().getAllByCart(cart.getId());
			List<CartVO> cartDetails = new ArrayList<CartVO>();
			for (CartItem cartItem : cartItems) {
				CartVO cartVO = new CartVO();
				cartVO.setId(cartItem.getId());
				cartVO.setQuantity(cartItem.getQuantity());
				Item item = mapperFactory.getItemMapper().findById(cartItem.getItem_id());
				cartVO.setItem_id(item.getId());
				cartVO.setUserName(mapperFactory.getUserMapper().getUsernameById(item.getUser_id()));
				cartVO.setName(item.getName());
				cartVO.setOgl_price(item.getOgl_price());
				cartVO.setPrice(item.getPrice());
				cartVO.setImg(item.getImg());
				cartDetails.add(cartVO);
			}
			return cartDetails;
		}else {
			return null;
		}
	}

	@Override
	public List<OrderListVO> checkMyOrder(String userId) {
		// TODO Auto-generated method stub
		List<Order> orders = mapperFactory.getOrderMapper().getByCustomer(userId);
		List<OrderListVO> VOs = new ArrayList<OrderListVO>();
		List<OrderItemVO> itemVOs;
		for (Order order : orders) {
			List<OrderItem> orderItems = mapperFactory.getOrderItemMapper().getByOrder(order.getId());
			itemVOs = new ArrayList<OrderItemVO>();
			for (OrderItem orderItem : orderItems) {
				OrderItemVO itemVO = new OrderItemVO();
				Item item = mapperFactory.getItemMapper().findById(orderItem.getItem_id());
				itemVO.setId(orderItem.getId());
				itemVO.setItem_id(item.getId());
				itemVO.setName(item.getName());
				itemVO.setPrice(item.getPrice());
				itemVO.setQuantity(orderItem.getQuantity());
				itemVO.setImg(item.getImg());
				itemVO.setStatus(orderItem.getStatus());
				itemVOs.add(itemVO);
			}
			OrderListVO listVO = new OrderListVO();
			listVO.setOrder(order);
			listVO.setOrderItemVO(itemVOs);
			VOs.add(listVO);
		}
		return VOs;
	}


	@Override
	public List<CartVO> getCartVO(List<Integer> cartItemIds) {
		// TODO Auto-generated method stub
		List<CartItem> cartItems = mapperFactory.getCartItemMapper().getByIds(cartItemIds);
		List<CartVO> cartVOs = new ArrayList<CartVO>();
		for (CartItem cartItem : cartItems) {
			CartVO cartVO = new CartVO();
			cartVO.setId(cartItem.getId());
			cartVO.setQuantity(cartItem.getQuantity());
			Item item = mapperFactory.getItemMapper().findById(cartItem.getItem_id());
			cartVO.setUserName(mapperFactory.getUserMapper().getUsernameById(item.getUser_id()));
			cartVO.setName(item.getName());
			cartVO.setOgl_price(item.getOgl_price());
			cartVO.setPrice(item.getPrice());
			cartVO.setImg(item.getImg());
			cartVOs.add(cartVO);
		}
		return cartVOs;
	}

	@Override
	public List<OrderListVO> checkMySellingOrder(String userId) {
		// TODO Auto-generated method stub
		List<Order> orders = mapperFactory.getOrderMapper().getByIds(mapperFactory.getOrderMapper().getOrderBySellerId(userId));
		List<OrderListVO> orderListVOs = new ArrayList<OrderListVO>();
		List<OrderItemVO> orderItemVOs;
		for (Order order : orders) {
			OrderListVO orderListVO = new OrderListVO();
			orderListVO.setOrder(order);
			List<OrderItem> orderItems = mapperFactory.getOrderItemMapper().getByOrderSeller(order.getId(), userId);
			orderItemVOs = new ArrayList<OrderItemVO>();
			for (OrderItem orderItem : orderItems) {
				OrderItemVO orderItemVO = new OrderItemVO();
				Item item = mapperFactory.getItemMapper().findById(orderItem.getItem_id());
				orderItemVO.setId(orderItem.getId());
				orderItemVO.setItem_id(item.getId());
				orderItemVO.setName(item.getName());
				orderItemVO.setPrice(item.getPrice());
				orderItemVO.setQuantity(orderItem.getQuantity());
				orderItemVO.setImg(item.getImg());
				orderItemVO.setStatus(orderItem.getStatus());
				orderItemVOs.add(orderItemVO);
			}
			orderListVO.setOrderItemVO(orderItemVOs);
			orderListVOs.add(orderListVO);
		}
		return orderListVOs;
	}


	@Override
	public List<Item> getMyItem(String userId) {
		return mapperFactory.getItemMapper().getBySeller(userId);
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(CookieConst.USER)) {
				LOGGER.info("cookie name:"+cookie.getName());
				cookie.setMaxAge(0);
				cookie.setPath(CookieConst.PATH);
				response.addCookie(cookie);
			}
		}
	}

}
