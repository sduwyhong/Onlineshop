package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import service.guest.GuestService;
import util.JSONUtil;
import VO.LoginVO;
import VO.ReviewVO;
import VO.SliderInfoVO;
import cn.dsna.util.images.ValidateCode;

import com.alibaba.fastjson.JSONObject;

import constant.ContentTypeConst;
import constant.FilePathConst;
import constant.JSONConst;
import entity.Category;
import entity.Item;
import entity.User;
import factory.ValidateCodeFactory;

@Controller
@RequestMapping("/guest")
public class GuestAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAction.class);

	@Autowired
	private GuestService guestService;

	//保存验证码(单例多线程)
	private ThreadLocal<String> validateCodeContainer = new ThreadLocal<String>() {

		@Override
		protected String initialValue() {
			return "";
		}

	};

	/**
	 * 将utf8解码成字符
	 * @param str
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/decode",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public String utf8Decoder(@RequestParam("str") String str) {
		return JSONUtil.createCustom(JSONConst.MESSAGE, URLDecoder.decode(str));
	}

	@ResponseBody
	@RequestMapping(value="/genImgAddress", method=RequestMethod.GET, produces=ContentTypeConst.JSON)
	public String genImgAddress(@RequestParam("fileName") String fileName) {
		return JSONUtil.createCustom(JSONConst.FILENAME, FilePathConst.ITEM_IMG_DIR + fileName);
	}

	/**
	 * 注册
	 * Post
	 * 参数数据类型：form
	 * req:username,password,gender,birthday,address,telephone
	 * @param user
	 * @param multipartFile img
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST, produces=ContentTypeConst.JSON)
	public String register(User user, @RequestParam MultipartFile multipartFile) {
		return guestService.register(user, multipartFile);
	}

	@RequestMapping(value="validateCode",method=RequestMethod.GET)
	public void validateCode(HttpServletResponse response) {
		ValidateCode validateCode = ValidateCodeFactory.getValidateCode();
		validateCodeContainer.set(validateCode.getCode());
		try {
			validateCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录
	 * Post
	 * 参数类型：form
	 * req：username,password(md5),autologin
	 * @param loginInfo 
	 * @param request
	 * @param response
	 * @return 登陆结果
	 */
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST, produces=ContentTypeConst.JSON)
	public String login(LoginVO loginInfo, HttpServletRequest request, HttpServletResponse response) {
		return guestService.login(loginInfo, validateCodeContainer.get(), request, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{userId}",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public String getUserInfo(@PathVariable("userId") String userId) {
		User user = guestService.findById(userId);
		if(user != null) {
			return JSONObject.toJSONString(user);
		}else {
			return JSONUtil.failure;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/userInfo/{itemId}",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public String getUserInfoByItem(@PathVariable("itemId") Integer itemId) {
		return guestService.findUserByItem(itemId);
	}
	
	/**
	 * 获取所有分类
	 * GET
	 * @return 所有分类信息
	 */
	@ResponseBody
	@RequestMapping("/category")
	public List<Category> getCategoryList() {
		return guestService.getCategoryList();
	}
	
	@ResponseBody
	@RequestMapping(value="/item/{itemId}",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public String getItemDetail(@PathVariable("itemId") Integer itemId){
		return guestService.findItemById(itemId);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/review/{itemId}",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<ReviewVO> getReviews(@PathVariable("itemId") Integer itemId) {
		return guestService.getReviews(itemId);
	}
	
	/**
	 * 获取分类下的所有商品
	 * GET
	 * 参数类型：URI
	 * req:categoryId
	 * @param categoryId
	 * @return 商品集合
	 */
	@ResponseBody
	@RequestMapping(value="/searchByCategory/{categoryId}", method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<Item> searchByCategory(@PathVariable("categoryId") Integer categoryId) {
		return guestService.searchByCategory(categoryId);
	}
	
	@ResponseBody
	@RequestMapping(value="/search/{keyword}",method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<Item> search(@PathVariable("keyword") String keyword) {
		try {
			keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		LOGGER.info("keyword:"+keyword);
		return guestService.search(keyword);
	}
	
	@ResponseBody
	@RequestMapping(value="/slider", method=RequestMethod.GET,produces=ContentTypeConst.JSON)
	public List<SliderInfoVO> getSliderInfo() {
		return guestService.getSliderInfo();
	}
}
