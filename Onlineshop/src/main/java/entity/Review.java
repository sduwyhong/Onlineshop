package entity;

import java.io.Serializable;
import lombok.Data;
@Data
public class Review implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4981361166421222858L;
	private Integer id;
	private Integer item_id;
	private String user_id;
	private String order_id;
	private Integer feedback = 0;//0好评 1中评 2差评
	private String content;//评论内容
}
