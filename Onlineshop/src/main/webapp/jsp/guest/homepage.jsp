<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp" %>
	<div id="homepage-content">
		<div id="homepage-content-left">
			<label>商品类目</label>
			<ul class="am-list" id="category_ul" style="margin-left: 200px;">
			</ul>
				<i class="am-icon-spinner am-icon-pulse"></i>
		</div>
		<div id="homepage-content-right">
			<div class="am-slider am-slider-default" data-am-flexslider id="demo-slider-0">
	  		  <ul class="am-slides">
			    <li>
			      <img style="height: 100%;width: 100%;" src="../img/slider/1.jpg" /></li>
			    <li>
			      <img style="height: 100%;width: 100%;" src="../img/slider/2.jpg" /></li>
			    <li>
			      <img style="height: 100%;width: 100%;" src="../img/slider/3.jpg" /></li>
			    <li>
			      <img style="height: 100%;width: 100%;" src="../img/slider/4.jpg" /></li>
			  </ul>
			</div>
		</div>
		<script type="text/javascript">
		function initCategory() {
			$.ajax({
				url:'guest/category',
				success:function(data) {
					for(var i in data) {
						var a = $('<a></a>');
						a.attr('href','jsp/guest/category.jsp?id='+data[i].id);
						a.text(data[i].name);
						var li = $('<li></li>');
						li.attr('style','width: 50px;');
						li.append(a);
						$('#category_ul').append(li);
					}
				}
			})
		}
		function initSliders() {
			$.ajax({
				url: 'guest/slider',
				success: function(data) {
					for(var i in data) {
						var li = $('<li></li>');
						var img = $('<img>');
						img.attr('style','width: 760px;');
						img.attr('src', data[i].img.replace('E:/SSMOnlineShop','..'));
						img.attr('alt', data[i].itemId);
						img.on('click', function() {
							location.href = 'jsp/guest/itemDetail.jsp?id='+$(this).attr('alt');
						})
						li.append(img);
						$('#slider').append(li);
					}
				}
			})
		}
		$(document).ajaxStop(function(){
			$('.am-icon-pulse').hide();
		})
		$(function() {
			initCategory();
		})
	</script>
	</div>
<%@ include file="footer.jsp" %>
  </body>
</html>
