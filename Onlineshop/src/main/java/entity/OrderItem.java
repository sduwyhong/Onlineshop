package entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class OrderItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6927258971352226888L;
	private Integer id;
	private Integer item_id;//订单内单个商品
	private Integer quantity = 1;//数量
	private String order_id;//所属订单
	private Integer status = 0;//0未付款，1付款未发货，2发货未签收，3已签收，4已评价，交易成功
}
