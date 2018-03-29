package VO;

import java.io.Serializable;

import lombok.Data;
@Data
public class SimpleOrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7458949159394930189L;
	private Integer itemId;
	private String address;
	private String receiverName;
	private String telephone;
}
