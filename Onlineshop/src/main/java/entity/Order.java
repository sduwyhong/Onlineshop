package entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1805536458075744327L;
	private String id;//uuid，手动生成
	private String ctm_id;//订单买家
	private Date gen_time;
	//收货地址
	private String address;
	//收货人姓名
	private String receiverName;
	//收货人电话
	private String telephone;
}
