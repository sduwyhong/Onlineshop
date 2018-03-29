package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.category.CategoryMapper;
import entity.Category;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:spring/applicationContext-dao.xml"}) 
public class TestCategoryDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestCategoryDao.class);
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	public void save(){
		 Category category = new Category();
		 category.setName("category1");
		 categoryMapper.save(category);
	}
	
	@Test
	public void delete(){
	}
}
