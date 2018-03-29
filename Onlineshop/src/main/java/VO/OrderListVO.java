package VO;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import entity.Order;
import entity.OrderItem;
@Data
public class OrderListVO implements Serializable {

	private static final long serialVersionUID = -7889542356166972997L;
	private Order order;
	private List<OrderItemVO> orderItemVO;

}
