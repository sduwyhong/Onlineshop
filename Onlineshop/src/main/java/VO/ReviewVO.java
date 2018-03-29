package VO;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReviewVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3676921437535681678L;
	private String username;
	private Integer feedback;
	private String content;
}
