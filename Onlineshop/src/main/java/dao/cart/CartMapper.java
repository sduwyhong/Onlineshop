package dao.cart;

import dao.base.BaseDao;
import entity.Cart;

public interface CartMapper extends BaseDao<Cart> {

	Cart getByUser(String userId);
}
