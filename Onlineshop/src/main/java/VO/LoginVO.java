package VO;

import java.io.Serializable;

import lombok.Data;
@Data
public class LoginVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2889562028342461552L;
	private String username;
	private String password;
	private String validateCode; 
	private String autoLogin = "";
}
