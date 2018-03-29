package service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import service.base.BaseService;
import VO.CartVO;
import VO.ComplicatedOrderVO;
import VO.FeedbackVO;
import VO.LoginVO;
import VO.OrderListVO;
import VO.ReviewVO;
import VO.SimpleOrderVO;
import VO.SliderInfoVO;
import entity.CartItem;
import entity.Category;
import entity.Item;
import entity.User;

public interface UserService extends BaseService<User> {
	/**
	 * 发布商品
	 * @param item
	 * @return 是否发布成功
	 */
	String publishItem(Item item, MultipartHttpServletRequest request, HttpServletResponse response);
	
	/**
	 * @param username
	 * @return
	 */
	User findByName(String username);
	
	String feedback(FeedbackVO feedback, String userId);

	String pay(Integer orderItemId);

	String delivery(Integer orderItemId);

	String receive(Integer orderItemId);

	void removeFromCart(List<Integer> cartItemIds);

	int genOrderByCart(ComplicatedOrderVO orderInfo, String customerId);

	String genOrder(SimpleOrderVO orderVO, String customerId);

	String modifyCartItem(CartItem cartItemInfo, String userId);

	String putIntoCart(Integer itemId, String userId);

	String removeItem(Integer itemId);
	
	List<CartVO> checkMyCart(String userId);
	
	List<OrderListVO> checkMyOrder(String userId);
	
	void logout(HttpServletRequest request, HttpServletResponse response);
	
	List<CartVO> getCartVO(List<Integer> cartItemIds);
	
	List<OrderListVO> checkMySellingOrder(String userId);
	


	
	List<Item> getMyItem(String userId);
	

	

}
