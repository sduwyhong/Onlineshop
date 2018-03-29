package factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dao.cart.CartItemMapper;
import dao.cart.CartMapper;
import dao.category.CategoryMapper;
import dao.item.ItemMapper;
import dao.order.OrderItemMapper;
import dao.order.OrderMapper;
import dao.review.ReviewMapper;
import dao.user.UserMapper;

/**
 * @author wyhong
 * @date 2018-3-28
 */
public class MapperFactory {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CartItemMapper cartItemMapper;
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private ReviewMapper reviewMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public CartItemMapper getCartItemMapper() {
		return cartItemMapper;
	}
	public CartMapper getCartMapper() {
		return cartMapper;
	}
	public ItemMapper getItemMapper() {
		return itemMapper;
	}
	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	public OrderItemMapper getOrderItemMapper() {
		return orderItemMapper;
	}
	public ReviewMapper getReviewMapper() {
		return reviewMapper;
	}
	
	
}
