package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class CartItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7551363341322954881L;
	private Integer id;
	private Integer quantity = 1;//数量
	private Integer item_id;//单件商品
	private Integer cart_id;//所属购物车
}
