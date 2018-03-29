package VO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import entity.Item;
@Data
public class CartVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9020440173991896192L;
	private Integer id;
	private Integer item_id;
	private Integer quantity;//数量
	private String userName;//卖家昵称
	private String name;//商品名
	private BigDecimal price;//价格
	private BigDecimal ogl_price;//原价
	private String img;
}
