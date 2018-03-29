package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.favorite.FavoriteItemMapper;
import dao.favorite.FavoriteMapper;
import dao.item.ItemMapper;
import dao.user.UserMapper;
import entity.FavoriteItem;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestFavoriteItemDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestFavoriteItemDao.class);
	
	@Autowired
	private FavoriteItemMapper favoriteItemMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private FavoriteMapper favoriteMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void save(){
		FavoriteItem favoriteItem = new FavoriteItem();
		favoriteItem.setFvt_id(favoriteMapper.getAll().get(0).getId());
		favoriteItem.setItem_id(itemMapper.getAll().get(0).getId());
		LOGGER.info(favoriteItemMapper.save(favoriteItem)+"");
	}
	
	@Test
	public void delete(){
		LOGGER.info(favoriteItemMapper.delete(favoriteItemMapper.getByFavorite(favoriteMapper.getByUser(userMapper.getAll().get(0).getId()).getId()).get(0).getId())+"");
	}
	
	@Test
	public void getAll(){
		List<FavoriteItem> list = favoriteItemMapper.getAll();
		for (FavoriteItem favoriteItem : list) {
			LOGGER.info(favoriteItem.toString());
		}
	}
}
