package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.description.DescriptionMapper;
import dao.item.ItemMapper;
import entity.Description;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestDescriptionDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestDescriptionDao.class);
	
	@Autowired
	private DescriptionMapper descriptionMapper;
	@Autowired
	private ItemMapper itemMapper;
	
	@Test
	public void save(){
		Description description = new Description();
		description.setItem_id(itemMapper.getAll().get(0).getId());
		descriptionMapper.save(description);
	}
	
	@Test
	public void delete(){
		descriptionMapper.delete(descriptionMapper.getAll().get(0).getId());
	}
	
	@Test
	public void getByItem(){
		LOGGER.info(descriptionMapper.getByItem(itemMapper.getAll().get(0).getId()).toString());
	}
}
