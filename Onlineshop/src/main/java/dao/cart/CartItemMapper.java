package dao.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dao.base.BaseDao;
import entity.CartItem;

public interface CartItemMapper extends BaseDao<CartItem> {

	List<CartItem> getAllByCart(int cartId);
	
	int updateQuantity(@Param("id") int id, @Param("quantity") int quantity);

	List<CartItem> getByIds(List<Integer> cartItemIds);

	int removeBatch(List<Integer> cartItemIds);
}
