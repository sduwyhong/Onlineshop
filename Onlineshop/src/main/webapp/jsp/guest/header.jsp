<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OnlineShop</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/jquery/jquery-2.0.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/jquery/jquery.form.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/../page-resource/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/../page-resource/jsp/client.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/../page-resource/amazeui/css/amazeui.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/../page-resource/amazeui/css/admin.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/jsp/client.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/jsp/md5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/jsp/bigDecimal.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/../page-resource/amazeui/js/amazeui.js"></script>
  </head>
  
  <body style="overflow: scroll;">
    <!-- navigation -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation" id="header" >
	  <!-- Brand and toggle get grouped for better mobile display -->
	  <div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	      <span class="sr-only">Toggle navigation</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	    </button>
	    <a class="navbar-brand" href="jsp/guest/homepage.jsp">wyhong的在线商城</a>
	  </div>
	
	  <!-- Collect the nav links, forms, and other content for toggling -->
	  <div class="collapse navbar-collapse" id="" style="margin-right: 20px;">
	    
	    <form class="navbar-form navbar-left" style="margin-left:600px;" role="search">
	      <div class="form-group">
	        <input type="text" class="form-control" name="keyword" style="width:200px;" placeholder="Search">
	      </div>
	      <button type="button" name="search" class="btn btn-default">搜索商品</button>
	    </form>
	    <ul class="nav navbar-nav navbar-right">
	      <li>
		      <a href="${pageContext.request.contextPath }/jsp/guest/login.jsp" id="login_area" >登录</a>
		      <a href="#" id="welcome_area"></a>
	      </li>
	      <li class="dropdown">
	        <a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心 <b class="caret"></b></a>
	        <ul class="dropdown-menu">
	          <li><a href="#">个人信息</a></li>
	          <li class="divider"></li>
	          <li><a href="jsp/client/cart.jsp">我的购物车</a></li>
	          <li class="divider"></li>
	          <li><a href="jsp/client/my_order.jsp">已买到宝贝</a></li>
	          <li class="divider"></li>
	          <li><a href="jsp/client/seller_order.jsp">已卖出宝贝</a></li>
	          <li class="divider"></li>
	          <li><a href="jsp/client/my_item.jsp">已发布宝贝</a></li>
	          <li class="divider"></li>
	          <li><a href="jsp/client/publish_item.jsp">发布宝贝</a></li>
	        </ul>
	      </li>
	      <li><a href="${pageContext.request.contextPath }/jsp/guest/register.jsp">注册</a></li>
	      <li><a href="javascript:logout()" id="logout_area">注销</a></li>
	    </ul>
	  </div><!-- /.navbar-collapse -->
	</nav>
	<script type="text/javascript" charset="UTF-8">
		function logout() {
			$.ajax({url:'user/logout'});
			location.reload();
		}
		function loginCheck() {
			var cookie = document.cookie;
			var field = cookie.split(';');
			var hasLogin = false;
			for(var i in field) {
				var info = field[i].split('=');
				if(info[0].trim() == '_user') {
					hasLogin = true;
					$.ajax({
						url:'guest/decode',
						type:'get',
						data:{
							str:info[1].trim()
						},
						success:function(data) {
							$('#welcome_area').text('欢迎您：'+data.message.split(';')[0]);
						},
						error:function(data) {
							$('#welcome_area').text('欢迎您');
							alert('decode progress failed')
							console.log(data);
						}
					})
				}
			}
			if(hasLogin) {
				$('#login_area').hide();
				$('#welcome_area').show();
				$('#logout_area').show();
			}else {
				$('#login_area').show();
				$('#welcome_area').hide();
				$('#logout_area').hide();
			}
		}
		function initSearch() {
			$('button[name=search]').on('click', function(){
				location.href = 'jsp/guest/search.jsp?keyword='+$('input[name=keyword]').val();
			})
		}
		$(function() {
			loginCheck();
			initSearch();
		})
	</script>
