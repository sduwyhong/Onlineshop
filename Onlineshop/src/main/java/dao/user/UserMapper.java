package dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import VO.LoginVO;
import VO.UserBaseInfoVO;
import dao.base.BaseDao;
import entity.User;

public interface UserMapper extends BaseDao<User> {

	void changePwd(@Param("id") String id, @Param("password") String password);
	
	List<UserBaseInfoVO> getVO();
	
	UserBaseInfoVO findVOById(String id);

	LoginVO login(@Param("username") String username, @Param("password") String password);

	User findByName(String username);

	String getUsernameById(String id);

	User findByItem(Integer itemId);
}
