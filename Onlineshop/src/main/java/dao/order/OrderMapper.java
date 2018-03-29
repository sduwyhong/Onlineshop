package dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dao.base.BaseDao;
import entity.Order;

public interface OrderMapper extends BaseDao<Order> {

	List<Order> getByCustomer(String id);
	
	List<String> getOrderBySellerId(String id);

	List<Order> getByIds(List<String> list);
}
