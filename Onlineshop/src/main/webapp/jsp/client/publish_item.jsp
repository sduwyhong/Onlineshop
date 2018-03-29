<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../guest/header.jsp" %>

<form action="" 
	class="am-form" id="item-form" method="post" enctype="multipart/form-data">
  <fieldset>
    <legend>发布商品</legend>
    <div class="am-form-group">
      <label for="name">商品名：</label>
      <input type="text" name="name" placeholder="商品名" required/>
    </div>
    
    <div class="am-form-group">
      <label for="price">价格：</label>
      <div class="am-input-group" style="width:100%;">
		  <span class="am-input-group-label">$</span>
	      <input type="text" name="price" placeholder="价格" required/>
	  </div>
    </div>
    
    <div class="am-form-group">
      <label for="ogl_price">原价：</label>
      <div class="am-input-group" style="width:100%;">
		  <span class="am-input-group-label">$</span>
	      <input type="text" name="ogl_price" placeholder="原价" required/>
	  </div>
    </div>
    
    <div class="am-form-group">
      <label for="repository">库存：</label>
      <div class="am-input-group" style="width:100%;">
		  <span class="am-input-group-label"><i class="am-icon-shopping-basket"></i></span>
	      <input type="text" name="repository" placeholder="库存（件）" required/>
	  </div>
    </div>

	<div class="am-form-group">
      <label for="category">所属分类(获取中<i class="am-icon-spinner am-icon-pulse"></i>)</label>
      <select name="category_id">
      </select>
      <span class="am-form-caret"></span>
    </div>
    
    <div class="am-form-group" style="width: 60%;">
      <label for="description">文本域</label>
      <textarea class="" rows="20" cols="30" name="description"></textarea>
    </div>
    
    <div class="am-form-group am-form-file">
	  <button type="button" class="am-btn am-btn-default am-btn-sm"><i class="am-icon-cloud-upload"></i>选择商品主图</button>
	  <input id="main-file" type="file" name="mainImg">
	  <div id="main-file-name" style="width: 100%;overflow:visible;"></div>
	</div>
	
	<div><button id="addImg" type="button" class="am-btn am-btn-warning">点我增加商品描述图</button></div>
	<div class="img_template" hidden="hidden" style="width: 40%;">
	    <div class="am-form-group am-form-file" style="width: 100%;">
		  <button type="button" class="am-btn am-btn-default am-btn-sm"><i class="am-icon-cloud-upload"></i>选择商品描述图</button>
		  <input type="file" class="sub_img" name="subImg">
		  <div id="sub-file-name" style="width: 100%;overflow:visible;"></div>
		</div>
		<button onclick="insert.call(this)" class="am-btn am-btn-success am-btn-xs" type="button">插入到文本</button>
	</div>
	<br>
	<br>
    <script>
    	$(function(){
    		initCategory();
    		initSubmit();
    	})
	    function initCategory() {
			var select = $('select[name=category_id]');
			$.ajax({
				url:'guest/category',
				success:function(data) {
					for(var i in data) {
						var option = $('<option></option>');
						option.attr('value',data[i].id);
						option.text(data[i].name);
						select.append(option);
					}
					$('label[for=category]').text('所属分类');
				}
			})
		}
    	function initSubmit() {
	   		$('#item-submit').on('click', function() {
	   			$('button:last').parent().children('div').show();
	       		$('#item-form').ajaxSubmit({
	       			url:'user/item',
	       			type:'POST',
	       			success:function(data) {
	       				$('button:last').parent().children('div').hide();
	       				if(data.message == 'permission denied') {
	       					alert('请登录！');
	       					location.href = 'jsp/guest/login.jsp';
	       				}else if(data.message == 'success'){
	       					alert('商品发布成功！');
	       					if(confirm('是否继续发布商品？')) {
	        					location.reload();
	       					}else {
	       						location.href = 'jsp/guest/homepage.jsp';
	       					}
	       				}else {
	       					alert('商品发布失败！');
	       					return false;
	       				}
	       			},
	       			error:function(data) {
	       				alert('服务器忙！');
	       				return false;
	       			}
	       		})
	       	});
    	}
    	$('#main-file').on('change', function() {
	      var fileNames = '';
	      $.each(this.files, function() {
	        fileNames += '<span class="am-badge">' + this.name + '</span> ';
	      });
	      $('#main-file').parent().children('div').html(fileNames);
	    });
    	$('#addImg').on('click', function(){
    		var template = $('.img_template:first').clone(true);
    		template.removeAttr('hidden');
    		template.children('div').children('input').attr('name',new Date().getTime());
    		$('.img_template:last').after(template);
    	})
    	$('.sub_img').on('change', function() {
  	      var fileNames = '';
  	      var ogl_name = "";
  	      $.each(this.files, function() {
  	    	ogl_name = this.name;
  	        fileNames += '<span class="am-badge">' + ogl_name + '</span> ';
  	      });
	 	    var $_this = $(this);
	       $.ajax({
	     	  url:'guest/genImgAddress',
	     	  type:'get',
	     	  data:{
	     		  fileName:ogl_name
	     	  },
	     	  success:function(data){
		      	$_this.parent().children('div').text(data.fileName);	
	     	  }
	       })
  	    });
    	function insert() {
    		var img = $('<img></img>');
    		img.attr('src',$(this).parent().children('div').children('div').text());
    		var desc = $('textarea[name=description]');
    		desc.val(desc.val()+img[0].outerHTML);
    	}
	</script>
    <div>
    	<button class="am-btn am-btn-secondary" id="item-submit" type="button">提交</button>
    	<div hidden="hidden">发布中<i class="am-icon-spinner am-icon-pulse"></i></div>
    </div>
  </fieldset>
</form>	

<%@ include file="../guest/footer.jsp" %>
  </body>
</html>
