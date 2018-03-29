package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.favorite.FavoriteMapper;
import dao.user.UserMapper;
import entity.Favorite;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestFavoriteDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestFavoriteDao.class);
	
	
	@Autowired
	private FavoriteMapper favoriteMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void save(){
		String userId = userMapper.getAll().get(0).getId();
		if(favoriteMapper.getByUser(userId) == null){
			Favorite favorite = new Favorite();
			favorite.setUser_id(userId);
			favoriteMapper.save(favorite);
		}else{
			LOGGER.info("the user already has a favorite");
		}
	}
	
	@Test
	public void delete(){
		favoriteMapper.delete(favoriteMapper.getAll().get(1).getId());
	}
	
	@Test
	public void getByUser(){
		LOGGER.info(favoriteMapper.getByUser(userMapper.getAll().get(0).getId()).toString());
	}
	
	@Test
	public void getAll(){
		favoriteMapper.getAll();
	}
}
