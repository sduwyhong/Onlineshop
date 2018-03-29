package VO;

import java.io.Serializable;

import lombok.Data;
@Data
public class FeedbackVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -646569003703663333L;
	private Integer feedback;
	private String content;
	private Integer orderItem_id;
	
}

