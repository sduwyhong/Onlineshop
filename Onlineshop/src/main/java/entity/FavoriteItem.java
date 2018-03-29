package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class FavoriteItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4639621065760297996L;
	private Integer id;
	private Integer item_id;//单件商品
	private Integer fvt_id;//所属购物车
}
