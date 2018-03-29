package service.guest.impl;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import service.base.impl.BaseServiceImpl;
import service.guest.GuestService;
import util.JSONUtil;
import util.LuceneUtil;
import VO.LoginVO;
import VO.ReviewVO;
import VO.SliderInfoVO;

import com.alibaba.fastjson.JSONObject;

import constant.CookieConst;
import constant.FilePathConst;
import constant.JSONConst;
import constant.SessionConst;
import constant.SpringConfigConst;
import dao.base.BaseDao;
import entity.Category;
import entity.Item;
import entity.User;
import factory.MapperFactory;

/**
 * @author wyhong
 * @date 2018-3-28
 */
public class GuestServiceImpl extends BaseServiceImpl<User> implements GuestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuestServiceImpl.class);

	@Autowired
	private MapperFactory mapperFactory;

	private ApplicationContext applicationContext;

	public GuestServiceImpl() {
		applicationContext = new ClassPathXmlApplicationContext(SpringConfigConst.APPLICATION,SpringConfigConst.APPLICATION_DAO);
		super.setBaseDao((BaseDao) applicationContext.getBean("userMapper"));
	}

	@Override
	public String register(User user, MultipartFile multipartFile) {
		try {
			String filePath =  FilePathConst.USER_IMG_DIR + multipartFile.getOriginalFilename();
			multipartFile.transferTo(new File(filePath));
			if(user.getGender().equals("male")) user.setGender("男");
			else user.setGender("女");
			user.setImg(filePath);
			user.setRegist_time(new Date());
			mapperFactory.getUserMapper().save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return JSONUtil.failure;
		}
		return JSONUtil.success;
	}

	@Override
	public String login(LoginVO loginInfo, String code, HttpServletRequest request, HttpServletResponse response) {
		if(!code.equalsIgnoreCase(loginInfo.getValidateCode())) 
			return JSONUtil.createCustom(JSONConst.MESSAGE, JSONConst.INCORRECT_CODE);
		LoginVO loginResult = mapperFactory.getUserMapper().login(loginInfo.getUsername(), loginInfo.getPassword());
		if(loginResult != null) {
			Cookie[] cookie = request.getCookies();
			Cookie newCookie = new Cookie(CookieConst.USER, URLEncoder.encode(loginResult.getUsername()+";"+loginResult.getPassword()));
			newCookie.setPath(CookieConst.PATH);
			//自动登录，设置cookie十天后过期
			if(loginInfo.getAutoLogin().equals("true")) newCookie.setMaxAge(60*60*24*10);
			//非自动登录，则直接登录，cookie30分钟失效
			else newCookie.setMaxAge(60*30);
			response.addCookie(newCookie);
			request.getSession().setAttribute(SessionConst.USER, mapperFactory.getUserMapper().findByName(loginResult.getUsername()));
			return JSONUtil.success;
		}else {
			//登录失败
			return JSONUtil.failure;
		}
	}
	
	@Override
	public List<Item> searchByCategory(Integer categoryId) {
		return mapperFactory.getItemMapper().getByCategory(categoryId);
	}

	@Override
	public List<Category> getCategoryList() {
		return mapperFactory.getCategoryMapper().getAll();
	}

	@Override
	public List<Item> search(String keywords) {
		LuceneUtil luceneUtil = new LuceneUtil();
		int[] index = null;
		try {
			index = luceneUtil.searchByTerm(LuceneUtil.FIELD_ITEM_NAME, keywords, 10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(index != null) {
			return mapperFactory.getItemMapper().search(index);
		}else {
			return new ArrayList<Item>();
		}
	}

	@Override
	public String findUserByItem(Integer itemId) {
		User user = mapperFactory.getUserMapper().findByItem(itemId);
		if(user != null) {
			return JSONObject.toJSONString(user);
		}else {
			return JSONUtil.failure;
		}
	}

	@Override
	public List<ReviewVO> getReviews(Integer itemId) {
		return mapperFactory.getReviewMapper().getReviewsByItem(itemId);
	}

	@Override
	public List<SliderInfoVO> getSliderInfo() {
		return mapperFactory.getItemMapper().getSlider();
	}

	@Override
	public String findItemById(Integer itemId) {
		Item item = mapperFactory.getItemMapper().findById(itemId);
		if(item != null) return JSONObject.toJSONString(item);
		else return JSONUtil.failure;
	}

	@Override
	public LoginVO login(String username, String password) {
		LoginVO loginVO = mapperFactory.getUserMapper().login(username, password);
		if(loginVO != null) return loginVO;
		else return null;
	}
}
