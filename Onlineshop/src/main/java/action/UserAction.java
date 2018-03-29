package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import service.user.UserService;
import util.JSONUtil;
import util.SessionUtil;
import VO.CartVO;
import VO.ComplicatedOrderVO;
import VO.FeedbackVO;
import VO.OrderListVO;
import VO.SimpleOrderVO;
import constant.ContentTypeConst;
import constant.JSONConst;
import entity.CartItem;
import entity.Item;

@Controller
@RequestMapping("/user/*")
public class UserAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAction.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 注销 
	 * Get
	 * @param request
	 * @param response
	 * @return 注销结果
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
	}


	/**
	 * 发布商品 
	 * POST
	 * 参数类型：form
	 * req：name,price,ogl_price,sales_vol,repository,img,sub_img,category_id
	 * @param item
	 * @param request
	 * @param response
	 * @return 发布结果
	 */
	@ResponseBody
	@RequestMapping(value="/item",method=RequestMethod.POST,produces=ContentTypeConst.JSON)
	public String publishItem(Item item,  MultipartHttpServletRequest request, HttpServletResponse response) {
		return userService.publishItem(item, request, response);
	}

	
	/**
	 * 删除商品
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/removeItem/{itemId}")
	public String removeItem(@PathVariable("itemId") Integer itemId) {
		return userService.removeItem(itemId);
	}

	/**
	 * 将商品加入购物车
	 * POST
	 * 参数类型：URI
	 * req：itemId
	 * @param itemId
	 * @param request
	 * @return 加入的结果
	 */
	@ResponseBody
	@RequestMapping(value="/putIntoCart/{itemId}",method=RequestMethod.POST,produces=ContentTypeConst.JSON)
	public String putIntoCart(@PathVariable("itemId") Integer itemId, HttpServletRequest request) {
		return userService.putIntoCart(itemId, SessionUtil.getUserId(request));
	}

	/**
	 * 修改购物车商品件数
	 * POST
	 * 参数类型：json
	 * req:item_id,quantity
	 * @param cartItem
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyCartItem")
	public String modifyCartItem(@RequestBody CartItem cartItemInfo, HttpServletRequest request) {
		return userService.modifyCartItem(cartItemInfo, SessionUtil.getUserId(request));
	}

	/**
	 * 生成订单（单件商品）
	 * POST
	 * 参数类型：URI
	 * req:itemId
	 * @param itemId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/genOrder",method=RequestMethod.POST,produces=ContentTypeConst.JSON)
	public String genOrder(SimpleOrderVO orderVO, HttpServletRequest request) {
		return userService.genOrder(orderVO, SessionUtil.getUserId(request));
	}
	
	@ResponseBody
	@RequestMapping(value="/cartItem",method=RequestMethod.POST,produces=ContentTypeConst.JSON)
	public List<CartVO> getCartItem(@RequestBody List<Integer> cartItemIds){
		return userService.getCartVO(cartItemIds);
	}
	
	@ResponseBody
	@RequestMapping(value="/genOrderByCart",method=RequestMethod.POST,produces=ContentTypeConst.JSON)
	public String genOrderByCart(@RequestBody ComplicatedOrderVO orderInfo, HttpServletRequest request) {
		if(orderInfo.getCartItemIds().size() > 0) {
			if(userService.genOrderByCart(orderInfo, SessionUtil.getUserId(request)) == 0) {
				return JSONUtil.createCustom(JSONConst.MESSAGE, JSONConst.REPOSITORY_NOT_ENOUGH);
			}else {
				userService.removeFromCart(orderInfo.getCartItemIds());
				return JSONUtil.success;
			}
		}else {
			return JSONUtil.failure;
		}
	}

	/**
	 * 付款
	 * post
	 * 参数类型：URI
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pay/{orderItemId}")
	public String pay(@PathVariable("orderItemId") Integer orderItemId) {
		return userService.pay(orderItemId);
	}

	/**
	 * 发货
	 * post
	 * 参数类型：URI
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delivery/{orderItemId}")
	public String delivery(@PathVariable("orderItemId") Integer orderItemId) {
		return userService.delivery(orderItemId);
	}

	/**
	 * 签收
	 * post
	 * 参数类型：URI
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/receive/{orderItemId}")
	public String receive(@PathVariable("orderItemId") Integer orderItemId) {
		return userService.receive(orderItemId);
	}

	/**
	 * 评价
	 * POST
	 * 参数类型：json
	 * req:feedbacks,content,orderItem_id
	 * @param feedback
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/feedback",method=RequestMethod.POST,produces=ContentTypeConst.JSON)
	public String feedback(FeedbackVO feedback, HttpServletRequest request) {
		return userService.feedback(feedback, SessionUtil.getUserId(request));
	}

	@ResponseBody
	@RequestMapping(value="/cart",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<CartVO> checkMyCart(HttpServletRequest request) {
		List<CartVO> cartVOs = userService.checkMyCart(SessionUtil.getUserId(request));
		if(cartVOs == null){
			cartVOs = new ArrayList<CartVO>();
		}
		return cartVOs;
	}
	
	@ResponseBody
	@RequestMapping(value="/order/customer",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<OrderListVO> checkMyOrder(HttpServletRequest request) {
		return userService.checkMyOrder(SessionUtil.getUserId(request));
	}
	
	@ResponseBody
	@RequestMapping(value="/order/seller",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<OrderListVO> checkMySellingOrder(HttpServletRequest request) {
		return userService.checkMySellingOrder(SessionUtil.getUserId(request));
	}
	
	@ResponseBody
	@RequestMapping(value="/myItem",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<Item> checkMyItem(HttpServletRequest request) {
		return userService.getMyItem(SessionUtil.getUserId(request));
	}
	
}
