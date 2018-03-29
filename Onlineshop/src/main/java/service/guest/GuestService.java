package service.guest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import service.base.BaseService;
import VO.LoginVO;
import VO.ReviewVO;
import VO.SliderInfoVO;
import entity.Category;
import entity.Item;
import entity.User;

/**
 * @author wyhong
 * @date 2018-3-28
 */
public interface GuestService extends BaseService<User> {
	
		String register(User user, MultipartFile multipartFile);
		
		String login(LoginVO loginInfo, String code, HttpServletRequest request, HttpServletResponse response);
		
		List<Item> searchByCategory(Integer categoryId);
		
		List<Category> getCategoryList();
		
		List<Item> search(String keywords);
		
		String findUserByItem(Integer itemId);
		
		String findItemById(Integer itemId);
		
		List<ReviewVO> getReviews(Integer itemId);
		
		List<SliderInfoVO> getSliderInfo();

		LoginVO login(String username, String password);
}
