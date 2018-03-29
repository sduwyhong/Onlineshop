package VO;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubItemVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2524880762940592062L;
	private Integer id;
	private String name;//商品名
	private BigDecimal price;//价格
	private BigDecimal ogl_price;//原价
	private Integer sales_vol = 0;//销量
	private String img;
	private Integer likes = 0;
	private String user_id;//商品的发布者
}
