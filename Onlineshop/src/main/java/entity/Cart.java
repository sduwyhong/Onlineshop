package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4891145283472591578L;
	private Integer id;
	private String user_id;//购物车所属用户
}
