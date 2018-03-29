<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../guest/header.jsp" %>
	<div id="my_item_header">
		<div class="my_item_img">商品图片</div>
		<div class="my_item_desc">商品名称</div>
		<div class="my_item_price">商品价格</div>
		<div class="my_item_repository">商品库存</div>
		<div class="my_item_volume">商品销量</div>
		<div class="my_item_publish_time">发布时间</div>
		<div class="my_item_op">操作</div>
	</div>
	<div class="divider_div" hidden="hidden" style="width: 70%;height: 1px;border: 1px solid #D3D3D3;"></div>
	<div id="my_item" class="item_template" hidden="hidden">
		<div class="my_item_img"><img style="height: 100%;width: 100%;"></div>
		<div class="my_item_desc"><h3 style="margin-top: 80px;"></h3></div>
		<div class="my_item_price"><p style="margin-top: 80px;"></p></div>
		<div class="my_item_repository"><p style="margin-top: 80px;"></p></div>
		<div class="my_item_volume"><p style="margin-top: 80px;"></p></div>
		<div class="my_item_publish_time"><p style="margin-top: 80px;"></p></div>
		<div class="my_item_op"><p style="margin-top: 80px;"><a>编辑</a>&nbsp;&nbsp;<a>下架该商品</a></p></div>
		<script type="text/javascript">
      		$(function() {
      			initItem();
      		})
      		function initItem() {
      			var keyword = location.search.substr(1).split('=')[1];
      			$.ajax({
      				url:'user/myItem',
      				success:function(data) {
      					if(data.message != undefined) {
      						alert('请登录！');
      						location.href = 'jsp/guest/login.jsp';
      					}else {
	      					if(!$.isEmptyObject(data)) {
		      					for(var i in data) {
			      					var template = $('.item_template:first').clone(true);
			      					template.removeAttr('hidden');
			      					template.children('div:eq(0)').children().attr('src',data[i].img.replace('E:/SSMOnlineShop','..'));
			      					template.children('div:eq(1)').children().text(data[i].name);
			      					template.children('div:eq(2)').children().text('￥'+data[i].price);
			      					template.children('div:eq(3)').children().text(data[i].repository);
			      					template.children('div:eq(4)').children().text(data[i].sales_vol);
			      					template.children('div:eq(5)').children().text(getDate(data[i].publish_time));
			      					$('.item_template:last').after(template);
			      					if(i > 0) {
				      					var divider = $('.divider_div:first').clone(true);
				      					divider.removeAttr('hidden');
				      					template.before(divider);
			      					}
		      					}
	      					}else {
	      						alert('还没有发布宝贝！');
	      						location.href = document.referrer;
	      					}
      					}
      				}
      			})
      		}
      		//转换时间戳
			function getDate(value){
				 return new Date(parseInt(("/Date("+value+")/").substr(6, 13))).toLocaleDateString();  
			}
      </script>
	</div>
<%@ include file="../guest/footer.jsp" %>
  </body>
</html>
