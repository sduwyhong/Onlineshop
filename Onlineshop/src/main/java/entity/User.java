package entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1125485398473172292L;
	private String id;//uuid，自动生成
	private String username;
	private String password;
	private String gender;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	private String address;
	private String telephone;
	private String img;
	private Date regist_time;
	//lombok生成的set方法为：setSeller   get方法为：isSeller()
	private boolean isSeller;//0买家，1卖家
}
