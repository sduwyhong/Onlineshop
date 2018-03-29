package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1866724415804016986L;
	private Integer id;
	private String name;//商品名
	private BigDecimal price;//价格
	private BigDecimal ogl_price;//原价
	private Integer sales_vol = 0;//销量
	private Integer repository;//库存
	private Date publish_time;//发布时间
	private String img;
	private String sub_img;
	private Integer likes = 0;
	private Integer category_id;//所属分类
	private String user_id;//商品的发布者
	private String description;
}
