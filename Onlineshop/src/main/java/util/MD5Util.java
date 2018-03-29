package util;

import org.springframework.util.DigestUtils;

public class MD5Util {

	public static String md5(String str) {
		return new String(DigestUtils.md5Digest(str.getBytes()));
	}
}
