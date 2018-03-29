package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.order.OrderMapper;
import dao.user.UserMapper;
import entity.Order;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestOrderDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestOrderDao.class);
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserMapper userMapper;
	
	
	@Test
	public void save(){
		Order order = new Order();
		order.setSeller_id(userMapper.getAll().get(0).getId());
		order.setCtm_id(userMapper.getAll().get(1).getId());
		orderMapper.save(order);
	}
	
	@Test
	public void delete(){
		orderMapper.delete(orderMapper.getAll().get(0).getId());
	}
	
	@Test
	public void getAll(){
		orderMapper.getAll();
	}
	
	@Test
	public void getBySeller(){
		LOGGER.info(orderMapper.getBySeller(userMapper.getAll().get(0).getId()).toString());
	}
	
	@Test
	public void getByCustomer(){
		orderMapper.getByCustomer(userMapper.getAll().get(1).getId());
	}
	
	@Test
	public void setStatus(){
		Order order = orderMapper.getAll().get(0);
		order.setStatus(1);
		orderMapper.setStatus(order.getId(),order.getStatus());
	}
}
