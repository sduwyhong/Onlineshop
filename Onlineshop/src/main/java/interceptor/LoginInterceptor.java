package interceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import service.guest.GuestService;
import service.user.UserService;
import VO.LoginVO;

import com.alibaba.fastjson.JSONObject;

import constant.ContentTypeConst;
import constant.CookieConst;
import constant.JSONConst;
import constant.SessionConst;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	@Autowired
	private GuestService guestService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		LOGGER.info("in LoginInterceptor");
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		response.setContentType(ContentTypeConst.JSON);
		Map<String, String> jsonMap = new HashMap<String, String>();
		if(session.getAttribute(SessionConst.USER) == null) {
			//没有登录
			LOGGER .info("hasn't login");
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConst.USER)) {
					//cookie未过期，自动登录
					LOGGER.info("has a valid cookie");
					String[] loginInfo = URLDecoder.decode(cookie.getValue()).split(";");
					String username = loginInfo[0];
					String password = loginInfo[1];
					LoginVO result = guestService.login(username, password);
					if(result != null) {
						session.setAttribute(SessionConst.USER, userService.findByName(username));
						return true;
					}else {
						LOGGER.info("has a valid login information");
						jsonMap.put(JSONConst.MESSAGE, JSONConst.PERMISSION_DENIED);
						response.getWriter().write(JSONObject.toJSONString(jsonMap));
						return false;
					}
				}
			}
		}else {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(CookieConst.USER)) {
					LOGGER.info("has login");
					return true;
				}
			}
			//有session没cookie
			//重新登陆
			session.invalidate();
		}
		LOGGER.info("doesn't have a required cookie");
		jsonMap.put(JSONConst.MESSAGE, JSONConst.PERMISSION_DENIED);
		response.getWriter().write(JSONObject.toJSONString(jsonMap));
		return false;
		
	}

}
