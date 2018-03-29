package test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.category.CategoryMapper;
import dao.item.ItemMapper;
import dao.user.UserMapper;
import entity.Item;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestItemDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestItemDao.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	public void save(){
		Item item = new Item();
		item.setCategory_id(categoryMapper.getAll().get(0).getId());
		item.setName("goods1");
		item.setPrice(new BigDecimal(25));
		item.setPublish_time(new Date());
		item.setImg("img.jpg");
		item.setRepository(5);
		item.setOgl_price(new BigDecimal(40));
		item.setUser_id(userMapper.getAll().get(0).getId());
		itemMapper.save(item);
	}
	
	@Test
	public void getAll(){
		LOGGER.info(itemMapper.getAll().get(0).toString());
	}
	@Test
	public void getByCategory() {
		List<Item> items = itemMapper.getByCategory(categoryMapper.getAll().get(0).getId());
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
	}
	
	@Test
	public void retrieveByPrice() {
		List<Item> items = itemMapper.retrieveByPrice(20, 30);
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
	}
	
	@Test
	public void getBySeller() {
		List<Item> items = itemMapper.getBySeller(userMapper.getAll().get(0).getId());
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
	}
	
	@Test
	public void updateVolume() {
		LOGGER.info(itemMapper.updateVolume()+"");
	}
	
	@Test
	public void updateRepository() {
		LOGGER.info(itemMapper.updateRepository(10)+"");
	}
	
	@Test
	public void updateLikes() {
		LOGGER.info(itemMapper.updateLikes()+"");
	}
}
