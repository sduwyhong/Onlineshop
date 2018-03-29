<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp" %>
	<div class="thumbnail item_brief_div" hidden="hidden">
      <img src="" alt="" style="width:250px;height:250px;">
      <div class="caption">
        <h3>商品名称</h3>
        <p style="color:#D3D3D3;text-decoration: line-through; ">商品原价</p><p style="color:#FF8C00;font-size: 20px; ">商品价格</p>
        <p><a href="#" class="btn btn-primary" >查看详情</a> <a href="#" class="btn btn-default">加入购物车</a></p>
      </div>
       <script type="text/javascript">
      		$(function() {
      			var categoryId = location.search.substr(1).split('=')[1];
      			$.ajax({
      				url:'guest/searchByCategory/'+categoryId,
      				success:function(data) {
      					if(!$.isEmptyObject(data)) {
	      					for(var i in data) {
		      					var template = $('.item_brief_div:first').clone(true);
		      					template.removeAttr('hidden');
		      					template.children('img').attr('src',data[i].img.replace('E:/SSMOnlineShop','..'));
		      					template.children('div').children('h3').text(data[i].name);
		      					template.children('div').children('p:first').text('￥'+data[i].ogl_price);
		      					template.children('div').children('p:eq(1)').text('￥'+data[i].price);
		      					template.children('div').children('p:last').children('a:first').attr('href','jsp/guest/itemDetail.jsp?itemId='+data[i].id);
		      					template.children('div').children('p:last').children('a:last').attr('href','javascript:putIntoCart('+data[i].id+')');
		      					$('.item_brief_div:last').after(template);
	      					}
      					}else {
      						alert('没有相关宝贝！');
      						location.href = document.referrer;
      					}
      				}
      			})
      		})
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
      </script>
    </div>

<%@ include file="footer.jsp" %>
  </body>
</html>
