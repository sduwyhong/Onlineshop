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
import dao.order.OrderMapper;
import dao.review.ReviewMapper;
import dao.user.UserMapper;
import entity.Review;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestReviewDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestReviewDao.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Test
	public void save(){
		Review review = new Review();
		review.setItem_id(itemMapper.getAll().get(0).getId());
		review.setOrder_id(orderMapper.getAll().get(0).getId());
		review.setUser_id(userMapper.getAll().get(0).getId());
		reviewMapper.save(review);
	}
	
	@Test
	public void delete(){
		reviewMapper.delete(reviewMapper.getAll().get(0).getId());
	}
	
	@Test
	public void findByOrder(){
		LOGGER.info(reviewMapper.getReviewByOrder(orderMapper.getAll().get(0).getId()).toString());
	}
	
	@Test
	public void findByItem(){
		List<Review> list = reviewMapper.getReviewsByItem(itemMapper.getAll().get(0).getId());
		for (Review review : list) {
			LOGGER.info(review.toString());
		}
	}
}
