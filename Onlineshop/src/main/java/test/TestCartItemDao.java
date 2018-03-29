package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.cart.CartItemMapper;
import dao.cart.CartMapper;
import dao.item.ItemMapper;
import dao.user.UserMapper;
import entity.CartItem;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestCartItemDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestCartItemDao.class);
	
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private CartItemMapper cartItemMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Test
	public void save(){
		CartItem cartItem = new CartItem();
		cartItem.setCart_id(cartMapper.getAll().get(0).getId());
		cartItem.setItem_id(itemMapper.getAll().get(0).getId());
		cartItem.setQuantity(2);
		cartItemMapper.save(cartItem);
	}
	
	@Test
	public void delete(){
	}
	
	@Test
	public void getAll(){
		List<CartItem> list = cartItemMapper.getAll();
		for (CartItem cartItem : list) {
			LOGGER.info(cartItem.toString());
		}
	}
}
