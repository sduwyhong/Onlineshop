package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4893214406407004976L;
	private Integer id;
	private String name;
}
