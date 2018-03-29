<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../guest/header.jsp" %>

	<div id="my_order_header">
		<div class="my_order_detail">
			<div class="my_order_img">商品图片</div>
			<div class="my_order_desc">商品信息</div>
			<div class="my_order_price">单价</div>
			<div class="my_order_quantity">数量</div>
			<div class="my_order_totalprice">金额</div>
			<div class="my_order_status">状态</div>
		</div>
	</div>
	
	<div id="my_order" class="order_Info" hidden="hidden" style="clear: both;">
		<div class="my_order_brief">
			<div class="my_order_time_id" style="width: auto;"><p></p></div>
		</div>
		<div class="my_order_detail orderItem_template" hidden="hidden" style="height: 79%;background-color: #00BFFF;">
			<div class="my_order_img"><img style="height: 100%;"></div>
			<div class="my_order_desc"><p style="margin-top: 50px;"></p></div>
			<div class="my_order_price"><p style="margin-top: 50px;"></p></div>
			<div class="my_order_quantity"><p style="margin-top: 50px;"></p></div>
			<div class="my_order_totalprice"><p style="margin-top: 50px;"></p></div>
			<div class="my_order_status"><p style="margin-top: 50px;"><button></button></p></div>
		</div>
		<script type="text/javascript">
			$(function(){
				initOrder();
			})
			function initOrder() {
				$.ajax({
					url:'user/order/seller',
					success:function(data){
						if(data.message != undefined) {
							alert('请登录！');
							location.href = 'jsp/guest/login.jsp';
						}else{
							for(var i in data) {
								//OrderListVO
								var order = data[i].order;
								var itemList = data[i].orderItemVO;
								var order_template = $('.order_Info:first').clone(true);
								order_template.removeAttr('hidden');
								order_template.children('div:eq(0)').children().children('p').text('订单号：'+order.id+'----------收货人姓名：'+order.receiverName);
								$('.order_Info:last').after(order_template);
								for(var j in itemList){
									var orderItem_template = $('.orderItem_template:first').clone(true);
									orderItem_template.removeAttr('hidden');
									var img = orderItem_template.children('.my_order_img').children('img');
									img.attr('src', itemList[j].img.replace('E:/SSMOnlineShop','..'));
									img.attr('alt',itemList[j].item_id);
									img.on('click', function(){
										location.href = 'jsp/guest/itemDetail.jsp?id='+$(this).attr('alt');
									});
									orderItem_template.children('.my_order_desc').children('p').text(itemList[j].name);
									var quantity = parseInt(itemList[j].quantity);
									var price = parseFloat(itemList[j].price);
									orderItem_template.children('.my_order_price').children('p').text("￥"+price);
									orderItem_template.children('.my_order_quantity').children('p').text(quantity);
									orderItem_template.children('.my_order_totalprice').children('p').text("￥"+quantity*price);
									var status = ''; 
									var _status = itemList[j].status;
									var button = orderItem_template.children('.my_order_status').children().children();
									if(_status == 0) {
										status = '未付款';
										button.attr('disabled', 'disabled');
									}else if(_status == 1) {
										status = '已付款，发货';
										button.attr('onClick', 'delivery('+itemList[j].id+')');
									}else if(_status == 2){
										status = '等待签收';
										button.attr('disabled', 'disabled');
									}else if(_status == 3){
										status = '买家已签收，等待买家评价';
										button.attr('disabled', 'disabled');
									}else{
										status = '交易成功';
										button.attr('disabled', 'disabled');
									}
									button.text(status);
									$('.order_Info:last').children('div:last').after(orderItem_template);
								}
							}
						}
					}
				})
			}
			function delivery(id){
				if(confirm('确认发货吗？')){
					$.ajax({
						url:'user/delivery/'+id,
						type:'POST',
						success:function(){
							alert('发货成功！');
							location.reload();
						},
						error:function(){
							alert('服务器忙！');
						}
					})
				}
			}
			function feedback(id){
				location.href = 'jsp/client/review.jsp?id='+id;
			}
		</script>
	</div>
	
<%@ include file="../guest/footer.jsp" %>
  </body>
</html>
