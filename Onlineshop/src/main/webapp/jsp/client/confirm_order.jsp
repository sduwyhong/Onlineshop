<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../guest/header.jsp" %>

	<div id="cart_items_header">
		<div class="cart_item_detail">
			<div class="cart_item_detail_img" style="margin-left: 60px;">商品图片</div>
			<div class="cart_item_detail_desc" style="margin-left: 30px;">商品信息</div>
			<div class="cart_item_detail_price" style="margin-left: 30px;">单价</div>
			<div class="cart_item_detail_quantity" style="margin-left: 30px;">数量</div>
			<div class="cart_item_detail_totalprice" style="margin-left: 30px;">金额</div>
		</div>
	</div>
	<div id="cart_items">
		<div class="cart_item" hidden="hidden">
			<div class="cart_item_detail" style="background-color: #FFF8DC;">
				<div class="cart_item_detail_img"style="margin-left: 60px;"><img style="width: 100%;height: 100%;"></div>
				<div class="cart_item_detail_desc"style="margin-left: 30px;"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_price"style="margin-left: 30px;"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_quantity"style="margin-left: 30px;"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_repository"style="margin-left: 30px;"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_totalprice"style="margin-left: 30px;"><p style="margin-top: 30px;"></p></div>
			</div>
		</div>
	</div>
	<form class="form-horizontal" role="form">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">收货地址</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" id="order_addr" placeholder="收货地址">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">收货人姓名</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" id="order_name" placeholder="姓名">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">收货人电话</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" id="order_tel" placeholder="手机号码">
	    </div>
	  </div>
		<div id="confirm_order">
			<div id="final_price">
				<p></p>
			</div>
			<div id="confirm_order_op">
				<div id="confirm_order_op_back">
					<a href="javascript:location.href = document.referrer;" style="font-size: 20px;">返回</a>
				</div>
				<div id="confirm_order_op_submit">
					<button type="button" class="btn btn-default btn-warning">确认订单</button>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				initInfo();
				initSubmit();
			})
			function initInfo(){
				var itemId = location.search.substr(1).split('=')[1];
				$.ajax({
					url:'guest/item/'+itemId,
					success:function(data) {
						if(data.message != undefined) {
							alert('请登录！');
							location.href = 'jsp/guest/login.jsp';
						}else {
							var template = $('.cart_item:first').clone(true);
							template.removeAttr('hidden');
							template.children('div').children('div:eq(0)').children('img').attr('src',data.img.replace('E:/SSMOnlineShop','..'))
							template.children('div').children('div:eq(1)').children('p').text(data.name);
							template.children('div').children('div:eq(2)').children('p').text(data.price);
							template.children('div').children('div:eq(3)').children('p').text('1');
							template.children('div').children('div:eq(4)').children('p').text(data.price);
							$('.cart_item:first').after(template);
							$('#final_price>p').text('实付款：￥'+data.price);
						}
					}
				})
			}
			function initSubmit(){
				$('button[type=button]').on('click',function(){
					$.ajax({
						url:'user/genOrder',
						type:'POST',
						data:{
							itemId:location.search.substr(1).split('=')[1],
							address:$('#order_addr').val(),
							receiverName:$('#order_name').val(),
							telephone:$('#order_tel').val()
						},
						success:function(data){
							if(data.message == 'success') {
								location.href = 'jsp/client/my_order.jsp';
							}else if(data.message == 'repository not enough') {
								alert('订单中有商品库存不足！');
								location.href = document.referrer;
							}else {
								alert('提交订单失败！');
							}
						},
						error:function(){
							alert('服务器忙！');
						}
					})
				})
			}
		</script>
	</form>
<%@ include file="../guest/footer.jsp" %>
  </body>
</html>
