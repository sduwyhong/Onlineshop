package VO;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class UserBaseInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4185829608999284333L;
	private String id;
	private String username;
//	private String password;
	private String gender;
	private Date birthday;
	private String address;
	private String telephone;
	private Date regist_time;
	private Boolean isSeller;//0买家，1卖家
	private String img;
}
