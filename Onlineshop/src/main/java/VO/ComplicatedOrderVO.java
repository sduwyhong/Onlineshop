package VO;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class ComplicatedOrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4783835137032058270L;
	private List<Integer> cartItemIds;
	private String address;
	private String receiverName;
	private String telephone;
}
