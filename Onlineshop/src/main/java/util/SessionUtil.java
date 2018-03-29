package util;

import javax.servlet.http.HttpServletRequest;

import constant.SessionConst;
import entity.User;

/**
 * @author wyhong
 * @date 2018-3-28
 */
public class SessionUtil {

	public static String getUserId(HttpServletRequest request) {
		return ((User) request.getSession().getAttribute(SessionConst.USER)).getId();
	}
}
