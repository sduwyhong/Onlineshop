package VO;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
@Data
public class OrderItemVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4497423894656849205L;
	private Integer id;
	private Integer item_id;
	private String img;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	private Integer status;
}
