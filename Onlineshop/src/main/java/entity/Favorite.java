package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class Favorite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2647499474609387012L;
	private Integer id;
	private String user_id;
}
