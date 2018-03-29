package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class Description implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -613234967670108886L;
	/*
	 *废弃 
	 */
	private Integer id;
	private Integer item_id;
	private String content;
	private String img = "no img";//最多3张
}
