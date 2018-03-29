<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp" %>
    <div style="width: 1px;height: 445px;;border: 1px solid #C0C0C0;margin-left: 940px;margin-top: 130px;position: absolute;"></div>
	<form action="" class="am-form" id="login-form" method="post">
  <fieldset>
    <legend>请登录</legend>
    <img src="../img/login/1.jpg" style="width: 30%;margin-top: 30px;margin-left: 18%;position: absolute;">
    <div class="am-form-group form_portion">
      <label for="username">用户名：</label>
      <div class="am-input-group" style="width:100%;">
		  <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
	      <input type="text" name="username" minlength="3" placeholder="输入用户名" required/>
	</div>
    </div>
    
    <div class="am-form-group form_portion">
      <label for="password">密码：</label>
      <div class="am-input-group" style="width:100%;">
		  <span class="am-input-group-label"><i class="am-icon-lock am-icon-fw"></i></span>
	      <input type="password" name="password" id="password" data-validation-message="密码" placeholder="输入密码" required/>
	</div>
    </div>
    
    <div class="am-form-group form_portion">
      <label for="validateCode">验证码：</label>
      <input type="text" name="validateCode" minlength="3" placeholder="输入验证码" required/>
      &nbsp;
      <img alt="" src="guest/validateCode" onclick="changeCode.call(this)">
    </div>
    <script type="text/javascript">
    	function changeCode() {
    		$(this).attr('src','user/validateCode?'+Math.random());
    	}
    	$(document).ajaxStart(function() {
    		$('button:last').parent().children('div').show();
    	})
    	$(function(){
    		$('#login-submit').on('click',function() {
        		var pwd = $('input[name=password]');
        		pwd.val(hex_md5(pwd.val()));
	    		$('#login-form').ajaxSubmit({
	    			url:'guest/login',
	    			type:'POST',
	    			success:function(data) {
	    				$('button:last').parent().children('div').hide();
	    				if(data.message == 'success') {
	    					alert('登录成功！');
	    					window.location.href=document.referrer;
	    					/*window.location.href = 'jsp/guest/homepage.jsp';*/
	    				}else if(data.message == 'incorrect validate code') {
	    					alert('验证码有误！');
	    					location.reload();
	    				}else {
	    					alert('用户名或密码有误！');
	    					location.reload();
	    				}
	    			},
	    			error:function(data) {
	    				alert('服务器忙！');
	    				console.log(data);
	    			}
	    		})
        	})
    	})
    </script>
	<div class="am-form-group form_portion">
      <label class="am-checkbox-inline">
        <input type="checkbox" value="true" name="autoLogin"> 自动登录
      </label>
    </div>
	
    <script>
	 
	</script>
    <div class="form_portion">
    	<button class="am-btn am-btn-secondary" id="login-submit" type="button">登录</button>
    	<div hidden="hidden">登录中<i class="am-icon-spinner am-icon-pulse"></i></div>
    </div>
  </fieldset>
</form>

<%@ include file="footer.jsp" %>
  </body>
</html>
