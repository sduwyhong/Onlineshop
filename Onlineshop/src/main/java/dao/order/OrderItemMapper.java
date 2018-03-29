package dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dao.base.BaseDao;
import entity.OrderItem;

public interface OrderItemMapper extends BaseDao<OrderItem> {

	List<OrderItem> getByOrder(String orderId);

	List<OrderItem> getByOrderSeller(@Param("orderId") String orderId, @Param("userId") String userId);

	int setStatus(@Param("id") Integer orderItemId, @Param("status") int status);
	
}
