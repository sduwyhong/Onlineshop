<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp" %>
	
	<div id="item">	
		<div id="item_img_para_seller">
			<div id="item_img">
				<img style="height:100%;">
			</div>
			<div id="item_para">
				<br>
				<strong style="font-size: 20px; ">销量：<i class="am-icon-spinner am-icon-pulse"></i></strong>
				<p style="color:#D3D3D3;text-decoration: line-through;font-size: 20px; ">原价：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p style="color:#FF8C00;font-size: 30px; ">现价：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p style="font-size: 20px; ">销量：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p style="font-size: 20px; ">库存：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p><a href="#" class="btn btn-primary btn-lg" >加入购物车</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="btn btn-default btn-lg" style="background-color: #FF8C00;color: #FFFAFA">购买</a></p>
			</div>
			<div id="item_seller" style="border: 2px outset;"><strong>卖家信息</strong>
				<br>
				<br>
				<img style="width: 150px;">
				<p>昵称：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p>性别：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p>地址：<i class="am-icon-spinner am-icon-pulse"></i></p>
				<p>注册时间：<i class="am-icon-spinner am-icon-pulse"></i></p>
			</div>
		</div>
		<div>
			<ul class="nav nav-tabs">
			  <li class="active" id="desc_li"><a href="javascript:showDesc()">商品详情</a></li>
			  <li id="review_li"><a href="javascript:showReview()">评价</a></li>
			</ul>
		</div>
		<div id="item_desc" style="overflow: scroll;">
			商品详细描述
		</div>
		<div id="item_review" style="overflow: scroll;">
			<div class="review_template" hidden="hidden">
				<div><p>来自<strong style="color: red;">--</strong>的<strong>--</strong>：</p><p><strong>--</strong></p></div>
				<div style="width: 90%;height: 1px;border: 1px solid #D3D3D3;"></div>
			</div>
		</div>
		<script type="text/javascript">
			function showDesc() {
				var desc = $('#desc_li');
				desc.addClass('active');
				$('#item_desc').show();
				var review = $('#review_li');
				review.removeClass('active');
				$('#item_review').hide();
			}
			var itemId = location.search.substr(1).split('=')[1];
			function showReview() {
				var review = $('#review_li');
				review.addClass('active');
				$('#item_review').show();
				var desc = $('#desc_li');
				desc.removeClass('active');
				$('#item_desc').hide();
				getReviews(itemId);
			}
			function getReviews(itemId) {
				$.ajax({
					url:'guest/review/'+itemId,
					success:function(data){
						for(var i in data) {
							var template = $('.review_template:first').clone(true);
							template.removeAttr('hidden');
							template.children('div:eq(0)').children('p:eq(0)').children('strong:eq(0)').text(data[i].username);
							var feedback = data[i].feedback;
							if(feedback == 0) {
								feedback = '好评';
							}else{
								feedback = feedback == 1 ? '中评':'差评';
							}
							template.children('div:eq(0)').children('p:eq(0)').children('strong:eq(1)').text(feedback);
							template.children('div:eq(0)').children('p:eq(1)').children().text(data[i].content);
							$('.review_template:last').after(template);
						}
					}
				})
			}
			$(document).ajaxStop(function(){
				$('.am-icon-pulse').hide();
			})
			$.ajax({
				url:'guest/item/'+itemId,
				success:function(data) {
					if(data.message != undefined) {
						alert('商品不存在噢！');
						history.go(-1);
					}else {
						$('#item_img>img').attr('src',data.img.replace('E:/SSMOnlineShop','..'));
						$('#item_para>strong').text(data.name);
						$('#item_para>p:eq(0)').text('原价：￥'+data.ogl_price);
						$('#item_para>p:eq(1)').text('现价：￥'+data.price);
						$('#item_para>p:eq(2)').text('销量：'+data.sales_vol);
						$('#item_para>p:eq(3)').text('库存：'+data.repository);
						$('#item_para>p:eq(4)>a:first').attr('href','javascript:putIntoCart('+data.id+')');
						$('#item_para>p:eq(4)>a:last').attr('href','javascript:genderOrder('+data.id+')');
						if(data.description != undefined) {
							$('#item_desc').html(data.description.replace(/E:\/SSMOnlineShop/g,'..').replace(/>/g,'><br>').replace(/\r\n/g,'<br>'));
						}else {
							$('#item_desc').text('暂时没有商品简介！！');
						}
					}
				}
			})
			function genderOrder(id) {
				var cookie = document.cookie;
				var field = cookie.split(';');
				var hasLogin = false;
				for(var i in field) {
					var info = field[i].split('=');
					if(info[0].trim() == '_user') {
						hasLogin = true;
					}
				}
				if(!hasLogin) {
					alert('请登录！');
					location.href = 'jsp/guest/login.jsp';
				}else {
					location.href = 'jsp/client/confirm_order.jsp?id='+id;
				}
			}
			function putIntoCart(id) {
      			$.ajax({
      				url:'user/putIntoCart/'+id,
      				type:'POST',
      				success:function(data) {
      					if(data.message == 'permission denied') {
	      					alert('请登录！');
      						location.href = 'jsp/guest/login.jsp'
      					}else if(data.message == 'success'){
	      					alert('加入购物车成功！');
	      					if(!confirm('继续选购吗？')){
	      						location.href = 'jsp/client/cart.jsp';
	      					}
      					}else {
      						alert('加入购物车失败！');
      					}
      				},
      				error:function() {
      					alert('服务器忙！');
      				}
      			})
      		}
			$.ajax({
				url:'guest/userInfo/'+itemId,
				success:function(data) {
					if(data.message != undefined) {
						alert('卖家不存在噢！');
					}else {
						$('#item_seller>img').attr('src',data.img.replace('E:/SSMOnlineShop','..'));
						$('#item_seller>p:eq(0)').text('昵称：'+data.username);
						$('#item_seller>p:eq(1)').text('性别：'+data.gender);
						$('#item_seller>p:eq(2)').text('地址：'+data.address);
						$('#item_seller>p:eq(3)').text('注册时间：'+getDate(data.regist_time));
					}
				}
			});
			//转换时间戳
			function getDate(value){
				 return new Date(parseInt(("/Date("+value+")/").substr(6, 13))).toLocaleDateString();  
			}
		</script>
	</div>

<%@ include file="footer.jsp" %>
  </body>
</html>
