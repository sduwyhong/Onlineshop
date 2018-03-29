package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.item.ItemMapper;
import dao.order.OrderItemMapper;
import dao.order.OrderMapper;
import dao.user.UserMapper;
import entity.OrderItem;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestOrderItemDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestOrderItemDao.class);
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Test
	public void save(){
		OrderItem orderItem = new OrderItem();
		orderItem.setItem_id(itemMapper.getAll().get(0).getId());
		orderItem.setOrder_id(orderMapper.getAll().get(0).getId());
		orderItem.setQuantity(3);
		orderItemMapper.save(orderItem);
	}
	
	@Test
	public void delete(){
		LOGGER.info(orderItemMapper.delete(orderItemMapper.getAll().get(0).getId())+"");
	}
	
	@Test
	public void getAll(){
		List<OrderItem> orderItems = orderItemMapper.getAll();
		for (OrderItem orderItem : orderItems) {
			LOGGER.info(orderItem.toString());
		}
	}
}
