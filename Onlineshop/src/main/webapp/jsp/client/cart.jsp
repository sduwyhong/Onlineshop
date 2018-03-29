<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../guest/header.jsp" %>
	<div id="cart_items_header">
		<div class="cart_item_detail">
			<div class="cart_item_detail_checkbox">全选</div>
			<div class="cart_item_detail_img">商品图片</div>
			<div class="cart_item_detail_desc">商品信息</div>
			<div class="cart_item_detail_price">单价</div>
			<div class="cart_item_detail_quantity">数量</div>
			<div class="cart_item_detail_totalprice">金额</div>
			<div class="cart_item_detail_op">操作</div>
		</div>
	</div>
	
	<div id="cart_items" >
		<div class="cart_item" hidden="hidden">
			<div class="cart_item_dealer" style="text-align: left;">
			</div>
			<div class="cart_item_detail item_info">
				<div class="cart_item_detail_checkbox"><input type="checkbox" name="select_cart_item" value="" style="margin-top: 40px;"></div>
				<div class="cart_item_detail_img"><img style="width: 100%;height: 100%;"></div>
				<div class="cart_item_detail_desc"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_price"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_quantity"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_totalprice"><p style="margin-top: 30px;"></p></div>
				<div class="cart_item_detail_op"><p style="margin-top: 30px;"><a href="" style="margin-top: 30px;">删除</a></p></div>
			</div>
		</div>
	</div>
	<div style="width: 100%;height: 20px;clear: both;"></div>
	<div id="function_footer" style="margin-top: 50px;">
		<div class="function_footer_selectAll">全选</div>
		<div class="function_footer_delete">删除选中项</div>
		<div class="function_footer_pay"><p>结算</p></div>
		<div class="function_footer_totalprice">合计：--元</div>
		<div class="function_footer_quantity">已选商品--件</div>
		<script type="text/javascript">
			$(function() {
				initCart();
				initCheckBox();
				initSubmit();
			})
			function initCart() {
				$.ajax({
					url:'user/cart',
					success:function(data) {
						if(data.message != undefined) {
							alert('请登录!');
							location.href = 'jsp/guest/login.jsp';
						}else {
							if($.isEmptyObject(data)) {
								alert('购物车为空！请继续选购^_^');
								location.href = document.referrer;
							}else {
								for(var i in data) {
									var template = $('.cart_item:first').clone(true);
									template.removeAttr('hidden');
									template.children('div:eq(0)').text('卖家：'+data[i].userName);
									var second_div = template.children('div:eq(1)');
									second_div.children('div:eq(0)').children('input').val(data[i].id);
									var img = second_div.children('div:eq(1)').children('img');
									img.attr('src',data[i].img.replace('E:/SSMOnlineShop','..'));
									img.attr('alt',data[i].item_id);
									img.on('click',function(){
										location.href = 'jsp/guest/itemDetail.jsp?id='+$(this).attr('alt');
									})
									second_div.children('div:eq(2)').children('p').text(data[i].name);
									second_div.children('div:eq(3)').children('p').text("￥"+data[i].price);
									second_div.children('div:eq(4)').children('p').text(data[i].quantity);
									var quantity = parseInt(data[i].quantity);
									var price = parseFloat(data[i].price);
									template.children('div:eq(1)').children('div:eq(5)').children('p').text("￥"+quantity*price);
									$('.cart_item:last').after(template);
								}
							}
						}
					},
					error:function() {
						alert('服务器忙');
					}
				})
			}
			function initCheckBox() {
				$('input[name=select_cart_item]').on('click', function(){
					var total_quantity = 0;
					var total_price = 0;
					$('input[name=select_cart_item]:checked').each(function(){
						total_quantity += parseInt($(this).parent().siblings('.cart_item_detail_quantity').children().text());
						total_price += parseFloat($(this).parent().siblings('.cart_item_detail_totalprice').children().text().substr(1));
					})
					$('.function_footer_quantity').text('已选商品'+total_quantity+'件');
					$('.function_footer_totalprice').text('合计：'+total_price+'元');
				})
			}
			function initSubmit() {
				$('.function_footer_pay').on('click', function(){
					var checked = $('input[name=select_cart_item]:checked');
					if(checked.size() > 0) {
						var cartItemId = '';
						checked.each(function(){
							cartItemId += $(this).val()+'+';
						});
						cartItemId = cartItemId.substr(0,cartItemId.length-1);
						location.href = 'jsp/client/confirm_multi_order.jsp?id=' + cartItemId;
					}else {
						alert('请选择要购买的商品！');
					}
				})
			}
		</script>
	</div>
<%@ include file="../guest/footer.jsp" %>
  </body>
</html>
