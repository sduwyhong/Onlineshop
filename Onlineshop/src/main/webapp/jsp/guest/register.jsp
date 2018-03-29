<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp" %>

<form action="" 
	class="am-form" id="register-form" method="post" enctype="multipart/form-data">
  <fieldset>
    <legend>用户注册</legend>
    <div class="am-form-group">
      <label for="username">用户名：</label>
      <input type="text" name="username" minlength="3" placeholder="输入用户名（至少 3 个字符）" required/>
    </div>

    <div class="am-form-group">
      <label for="password">密码：</label>
      <input type="password" name="password" id="password" data-validation-message="密码" placeholder="输入密码" required/>
    </div>
    
	<div class="am-form-group">
      <label>性别： </label>
      <br>
      <label class="am-radio-inline">
        <input type="radio"  value="male" name="gender" checked="checked" required> <i class="am-icon-male am-icon-lg"></i>
      </label>
      <label class="am-radio-inline">
        <input type="radio" value="female" name="gender"> <i class="am-icon-female am-icon-lg"></i>
      </label>
    </div>
    
   	<div id="date-picker">
   		<input type="text" class="am-form-field" name="birthday" placeholder="日历组件" data-am-datepicker="{theme: 'success'}" readonly/>
   	</div>
    
    <div class="am-form-group">
      <label for="address">地址：</label>
      <input type="text" name="address" placeholder="输入地址" required/>
    </div>

	<div class="am-form-group">
      <label for="telephone">手机号码：</label>
      <input type="text" name="telephone" placeholder="输入手机号码" required/>
    </div>
    
    <div class="am-form-group am-form-file">
	  <button type="button" class="am-btn am-btn-default am-btn-sm">选择你的头像</button>
	  <input id="doc-form-file" type="file" name="multipartFile">
	</div>
	<div id="file-list"></div>
	<br>
	<br>
    <script>
	  $(function() {
	    $('#doc-form-file').on('change', function() {
	      var fileNames = '';
	      $.each(this.files, function() {
	        fileNames += '<span class="am-badge">' + this.name + '</span> ';
	      });
	      $('#file-list').html(fileNames);
	    });
	    $('#register-submit').on('click', function() {
	    	digest();
	    	$('#register-form').ajaxSubmit({
	    		url:'guest/register',
	    		type:'POST',
	    		success:function(data){
	    			if(data.message == 'success') {
	    				alert("注册成功！");
	    				window.location.href = 'jsp/guest/login.jsp'
	    			}else {
	    				alert("注册失败！");
	    			}
	    		},
	    		error:function(data){
	    			alert("注册失败！原因："+data.message);
	    		}
	    	});
	    })
	  });
	  function digest() {
		  var pwd = $('input[id=password]').val();
		  $('input[id=password]').val(hex_md5(pwd));
	  }
	</script>
    <div>
    	<button class="am-btn am-btn-secondary" id="register-submit" type="button">提交</button>
    </div>
  </fieldset>
</form>
	

<%@ include file="footer.jsp" %>
  </body>
</html>
