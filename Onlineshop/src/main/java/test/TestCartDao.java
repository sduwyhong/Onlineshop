package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.cart.CartMapper;
import dao.user.UserMapper;
import entity.Cart;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestCartDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestCartDao.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CartMapper cartMapper;
	
	@Test
	public void save(){
		Cart cart = new Cart();
		cart.setUser_id(userMapper.getAll().get(0).getId());
		cartMapper.save(cart);
	}
	
	@Test
	public void delete(){
		cartMapper.delete(cartMapper.getAll().get(0).getId());
	}
	
	@Test
	public void getByUser(){
		LOGGER.info(cartMapper.getByUser(userMapper.getAll().get(0).getId()).toString());
	}
}
