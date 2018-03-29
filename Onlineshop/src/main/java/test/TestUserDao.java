package test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import constant.SpringConfigConst;

import service.user.UserService;
import VO.UserBaseInfoVO;
import dao.user.UserMapper;
import entity.User;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={SpringConfigConst.APPLICATION,
								 SpringConfigConst.APPLICATION_DAO,
								 SpringConfigConst.APPLICATION_SERVICE}) 
public class TestUserDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestUserDao.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;
	
	@Test
	public void save(){
		User user = new User();
		user.setUsername("tom");
		user.setAddress("sd");
		user.setGender("男");
		user.setPassword("123");
		user.setBirthday(new Date());
		user.setRegist_time(new Date());
		user.setSeller(false);
		user.setTelephone("17864154784");
		userMapper.save(user);
	}
	
	@Test
	public void delete(){
		userMapper.delete(userMapper.getAll().get(0).getId());
	}
	
	@Test
	public void update(){
		User user = userMapper.getAll().get(0);
		user.setPassword("123987456");
		userMapper.changePwd(user.getId(),user.getPassword());
	}
	
	@Test
	public void getAll(){
		List<User> userList = userMapper.getAll();
		for (User user : userList) {
			LOGGER.info(user.toString());
		}
	}
	
	@Test
	public void getVO(){
		List<UserBaseInfoVO> userList = userMapper.getVO();
		for (UserBaseInfoVO user : userList) {
			LOGGER.info(user.toString());
		}
	}
	
	@Test
	public void findVOById(){
		LOGGER.info(userMapper.findVOById(userMapper.getAll().get(0).getId()).toString());
	}
	
	@Test
	public void serviceGetAll(){
		List<User> userList = userService.getAll();
		for (User user : userList) {
			LOGGER.info(user.toString());
		}
	}
	
	@Test
	public void serviceSave(){
		User user = new User();
		user.setUsername("service");
		user.setAddress("sd");
		user.setGender("男");
		user.setPassword("123");
		user.setBirthday(new Date());
		user.setRegist_time(new Date());
		user.setSeller(false);
		user.setTelephone("17864154784");
		userService.save(user);
	}
}
