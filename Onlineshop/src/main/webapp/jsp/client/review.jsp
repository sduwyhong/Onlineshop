<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../guest/header.jsp" %>

<div id="review">
	<div><h1>评价商品</h1></div>
	<br><br>
	<div id="review_comment">
		<label>给个评价吧！</label>
		<input type="radio" value="0" name="feedback" checked="checked">好评
		&nbsp;&nbsp;
		<input type="radio" value="1" name="feedback">中评
		&nbsp;&nbsp;
		<input type="radio" value="2" name="feedback">差评
	</div>
	<div id="review_content">
		<label>具体评价：</label>
		<textarea name="content" cols="60" rows="5"></textarea>
	</div>
	<div><button>提交评价</button></div>
	<script type="text/javascript">
		$(function(){
			initButton();
		})
		function initButton(){
			$('button').on('click', function(){
				$.ajax({
					url:'user/feedback',
					type:'POST',
					data:{
						feedback: $('input[name=feedback]').val(),
						content: $('textarea[name=content]').val(),
						orderItem_id: location.search.substr(1).split('=')[1]
					},
					success:function(data){
						if(data.message == 'success') {
							alert('评价成功！');
							location.href = 'jsp/client/my_order.jsp';
						}else {
							alert('评价失败！');
						}
					},
					error:function() {
						alert('服务器忙！');
					}
				})
			})
		}
	</script>
</div>

<%@ include file="../guest/footer.jsp" %>
  </body>
</html>
