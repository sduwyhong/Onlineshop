<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-2.0.3.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
	
  </head>
  
  <body>
    <h1>h1<small>small</small></h1>
    <p class="lead">paragraph</p>
    <strong>rendered as bold text</strong>
    <em>rendered as italicized text</em>
    <p class="text-left">Left aligned text.</p>
	<p class="text-center">Center aligned text.</p>
	<p class="text-right">Right aligned text.</p>
	<p class="text-muted">Fusce dapibus, tellus ac cursus commodo, tortor mauris nibh</p>
	<p class="text-primary">Fusce dapibus, tellus ac cursus commodo, tortor mauris nibh</p>
	<p class="text-success">Fusce dapibus, tellus ac cursus commodo, tortor mauris nibh</p>
	<p class="text-info">Fusce dapibus, tellus ac cursus commodo, tortor mauris nibh</p>
	<p class="text-warning">Fusce dapibus, tellus ac cursus commodo, tortor mauris nibh</p>
	<p class="text-danger">Fusce dapibus, tellus ac cursus commodo, tortor mauris nibh</p>
	<abbr title="attribute">attr</abbr>
	<blockquote>
	  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
	  <small>Someone famous in <cite title="Source Title">Source Title</cite></small>
	</blockquote>
	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover table-condensed">
	  		<tr>
	  			<th class="success">
	  				a
	  			</th>
	  			<th class="success">
	  				b
	  			</th>
	  			<th class="success">
	  				c
	  			</th>
	  		</tr>
	  		<tr>
	  			<td>
	  				1
	  			</td>
	  			<td>
	  				2
	  			</td>
	  			<td>
	  				3
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>
	  				4
	  			</td>
	  			<td>
	  				5
	  			</td>
	  			<td>
	  				6
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>
	  				7
	  			</td>
	  			<td>
	  				8
	  			</td>
	  			<td>
	  				9
	  			</td>
	  		</tr>
		</table>
	</div>
	<form  role="form">
	  <div class="form-group">
	    <label for="exampleInputEmail1">Email address</label>
	    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="输入邮箱">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1">Password</label>
	    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="密码">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputFile">File input</label>
	    <input type="file" id="exampleInputFile">
	    <p class="help-block">Example block-level help text here.</p>
	  </div>
	  <div class="checkbox">
	    <label>
	      <input type="checkbox"> Check me out
	    </label>
	  </div>
	  <button type="submit" class="btn btn-default">Submit</button>
	   
	</form>
	<form class="form-inline" role="form">
	 <fieldset disabled>
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputEmail2">Email address</label>
	    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
	  </div>
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword2">Password</label>
	    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
	  </div>
	  <div class="checkbox">
	    <label>
	      <input type="checkbox"> Remember me
	    </label>
	  </div>
	  <button type="submit" class="btn btn-default">Sign in</button>
	  </fieldset>
	</form>
	<form class="form-horizontal" role="form">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <div class="checkbox">
	        <label>
	          <input type="checkbox"> Remember me
	        </label>
	      </div>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Sign in</button>
	    </div>
	  </div>
	</form>
	<textarea class="form-control" rows="3"></textarea>
	<div class="checkbox">
	  <label>
	    <input type="checkbox" value="">
	    Option one is this and that&mdash;be sure to include why it's great
	  </label>
	</div>
	
	<div class="radio">
	  <label>
	    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
	    Option one is this and that&mdash;be sure to include why it's great
	  </label>
	</div>
	<div class="radio">
	  <label>
	    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
	    Option two can be something else and selecting it will deselect option one
	  </label>
	</div>
	<label class="checkbox-inline">
	  <input type="checkbox" id="inlineCheckbox1" value="option1"> 1
	</label>
	<label class="checkbox-inline">
	  <input type="checkbox" id="inlineCheckbox2" value="option2"> 2
	</label>
	<label class="checkbox-inline">
	  <input type="checkbox" id="inlineCheckbox3" value="option3"> 3
	</label>
	<select class="form-control">
	  <option>1</option>
	  <option>2</option>
	  <option>3</option>
	  <option>4</option>
	  <option>5</option>
	</select>
	
	<select multiple class="form-control">
	  <option>1</option>
	  <option>2</option>
	  <option>3</option>
	  <option>4</option>
	  <option>5</option>
	</select>
	<form class="form-horizontal" role="form">
	  <div class="form-group">
	    <label class="col-sm-2 control-label">Email</label>
	    <div class="col-sm-10">
	      <p class="form-control-static">email@example.com</p>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="inputPassword" placeholder="Password">
	    </div>
	  </div>
	</form>
	<br>
	<br>
	<input class="form-control" type="text" placeholder="Disabled input here..." disabled>
	<div class="form-group has-success col-xs-2">
	  <label class="control-label" for="inputSuccess">Input with success</label>
	  <input type="text" class="form-control input-lg" id="inputSuccess">
	</div>
	<div class="form-group has-warning  col-xs-3">
	  <label class="control-label" for="inputWarning">Input with warning</label>
	  <input type="text" class="form-control" id="inputWarning">
	</div>
	<div class="form-group has-error  col-xs-4">
	  <label class="control-label" for="inputError">Input with error</label>
	  <input type="text" class="form-control input-sm" id="inputError">
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<!-- Standard button -->
	<button type="button" class="btn btn-default btn-lg">Default</button>
	
	<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
	<button type="button" class="btn btn-primary btn-sm">Primary</button>
	
	<!-- Indicates a successful or positive action -->
	<button type="button" class="btn btn-success btn-xs">Success</button>
	
	<!-- Contextual button for informational alert messages -->
	<button type="button" class="btn btn-info btn-block">Info</button>
	
	<!-- Indicates caution should be taken with this action -->
	<button type="button" class="btn btn-warning">Warning</button>
	
	<!-- Indicates a dangerous or potentially negative action -->
	<button type="button" class="btn btn-danger">Danger</button>
	
	<!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
	<button type="button" class="btn btn-link">Link</button>
	
	<button type="button" class="btn btn-lg btn-primary" disabled>Primary button</button>
	<button type="button" class="btn btn-default btn-lg" disabled="disabled">Button</button>
	
	<img src="..." alt="..." class="img-rounded">
	<img src="..." alt="..." class="img-circle">
	<img src="..." alt="..." class="img-thumbnail">
	
	<h1 class="text-hide">Custom heading</h1>
	
	<button type="button" class="btn btn-default btn-lg">
  		<span class="glyphicon glyphicon-search"></span>
	</button>
	<button type="button" class="btn btn-default btn-lg">
  		<span class="glyphicon glyphicon-trash"></span>
	</button>
	<button type="button" class="btn btn-default btn-lg">
  		<span class="glyphicon glyphicon-star"></span> Star
	</button>
	<br>
	btn-group
	<div class="btn-group">
	  <button type="button" class="btn btn-default">Left</button>
	  <button type="button" class="btn btn-default">Middle</button>
	  <button type="button" class="btn btn-default">Right</button>
	</div>
	<br>
	btn-toolbar
	<div class="btn-toolbar" role="toolbar">
	  	<div class="btn-group btn-group-lg">
		  <button type="button" class="btn btn-default">Left</button>
		  <button type="button" class="btn btn-default">Middle</button>
		  <button type="button" class="btn btn-default">Right</button>
		</div>
	  	<div class="btn-group btn-group-sm">
		  <button type="button" class="btn btn-default">Left</button>
		  <button type="button" class="btn btn-default">Middle</button>
		  <button type="button" class="btn btn-default">Right</button>
		</div>
	  	<div class="btn-group btn-group-xs">
		  <button type="button" class="btn btn-default">Left</button>
		  <button type="button" class="btn btn-default">Middle</button>
		  <button type="button" class="btn btn-default">Right</button>
		</div>
	</div>
	<div class="btn-group btn-group-vertical">
  <button type="button" class="btn btn-default">1</button>
  <button type="button" class="btn btn-default">2</button>

  	<div class="btn-group">
	    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	      Dropdown
	      <span class="caret"></span>
	    </button>
	    <ul class="dropdown-menu">
	      <li><a href="#">Dropdown link</a></li>
	      <li><a href="#">Dropdown link</a></li>
	    </ul>
	  </div>
	</div>
	<br>
	<br>
	<ul class="nav nav-tabs nav-justified">
	  <li class="active"><a href="#">Home</a></li>
	  <li><a href="#">Profile</a></li>
	  <li><a href="#">Messages</a></li>
	  <li><a href="#">Shop</a></li>
	</ul>
	<br>
	<ul class="nav nav-pills nav-stacked">
		  <li class="active"><a href="#">Home</a></li>
		  <li><a href="#">Profile</a></li>
		  <li><a href="#">Messages</a></li>
	</ul>
	<br>
	<!-- navigation -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
	  <!-- Brand and toggle get grouped for better mobile display -->
	  <div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	      <span class="sr-only">Toggle navigation</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	    </button>
	    <a class="navbar-brand" href="#">Brand</a>
	  </div>
	
	  <!-- Collect the nav links, forms, and other content for toggling -->
	  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">Link</a></li>
	      <li><a href="#">Link</a></li>
	      <li class="dropdown">
	        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
	        <ul class="dropdown-menu">
	          <li><a href="#">Action</a></li>
	          <li><a href="#">Another action</a></li>
	          <li><a href="#">Something else here</a></li>
	          <li class="divider"></li>
	          <li><a href="#">Separated link</a></li>
	          <li class="divider"></li>
	          <li><a href="#">One more separated link</a></li>
	        </ul>
	      </li>
	    </ul>
	    <form class="navbar-form navbar-left" style="margin-left:500px;" role="search">
	      <div class="form-group">
	        <input type="text" class="form-control" style="width:200px;" placeholder="Search">
	      </div>
	      <button type="submit" class="btn btn-default">Submit</button>
	    </form>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="#">Link</a></li>
	      <li class="dropdown">
	        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
	        <ul class="dropdown-menu">
	          <li><a href="#">Action</a></li>
	          <li><a href="#">Another action</a></li>
	          <li><a href="#">Something else here</a></li>
	          <li class="divider"></li>
	          <li><a href="#">Separated link</a></li>
	        </ul>
	      </li>
	    </ul>
	  </div><!-- /.navbar-collapse -->
	</nav>
	<ul class="pagination">
	  <li><a href="#">&laquo;</a></li>
	  <li><a href="#">1</a></li>
	  <li><a href="#">2</a></li>
	  <li><a href="#">3</a></li>
	  <li><a href="#">4</a></li>
	  <li><a href="#">5</a></li>
	  <li><a href="#">&raquo;</a></li>
	</ul>
	<a href="#">Inbox <span class="badge">42</span></a>
	<div class="jumbotron">
	  <h1>Hello, world!</h1>
	  <p>...</p>
	  <p><a class="btn btn-primary btn-lg" role="button">Learn more</a></p>
	</div>
	<div class="row">
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
	      <img data-src="holder.js/300x200" alt="...">
	      <div class="caption">
	        <h3>Thumbnail label</h3>
	        <p>fefewregrwegreytgtryhtryhwerytwertrywetqtew</p>
	        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="alert alert-success" style="width: 50%;">alert alert-success</div>
	<div class="alert alert-info">alert alert-info</div>
	<div class="alert alert-warning">alert alert-warning</div>
	<div class="alert alert-danger">alert alert-danger</div>
	<div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">
	    <span class="sr-only">30% Complete</span>
	  </div>
	</div>
	<ul class="list-group">
	  <li class="list-group-item">
	    <span class="badge">14</span>
	    Cras justo odio
	  </li>
	</ul>
	<div class="list-group">
	  <a href="#" class="list-group-item active">
	    Cras justo odio
	  </a>
	  <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
	  <a href="#" class="list-group-item">Morbi leo risus</a>
	  <a href="#" class="list-group-item">Porta ac consectetur ac</a>
	  <a href="#" class="list-group-item">Vestibulum at eros</a>
	</div>
	<div class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
	        <p>One fine body&hellip;</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<br>
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="width: 950px;margin: 0 auto;">
	  <!-- Indicators -->
	  <ol class="carousel-indicators">
	    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
	  </ol>
	  <!-- Wrapper for slides -->
	  <div class="carousel-inner" >
	    <div class="item active">
	      <img style="width:950px;height:530px; " src="${pageContext.request.contextPath }/img/1.jpg" alt="...">
	       <div class="carousel-caption">
		    <h1>阿尔法罗密欧</h1>
		    <p>giulia</p>
		  </div>
	    </div>
	    <div class="item">
	      <img style="width:950px;height:530px;" src="${pageContext.request.contextPath }/img/2.jpg" alt="...">
	       <div class="carousel-caption">
		    <h1>布加迪</h1>
		    <p>威龙</p>
		  </div>
	    </div>
	    <div class="item">
	      <img style="width:950px;height:530px;" src="${pageContext.request.contextPath }/img/3.jpg" alt="...">
	       <div class="carousel-caption">
		    <h1>unknown</h1>
		    <p>unknown</p>
		  </div>
	    </div>
	    <div class="item">
	      <img style="width:950px;height:530px;" src="${pageContext.request.contextPath }/img/4.jpg" alt="...">
	      <div class="carousel-caption">
		    <h1>法拉利</h1>
		    <p>laferrari</p>
		  </div>
	    </div>
	    <div class="item">
	      <img style="width:950px;height:530px;" src="${pageContext.request.contextPath }/img/5.jpg" alt="...">
	      <div class="carousel-caption">
		    <h1>布加迪</h1>
		    <p>chiron</p>
		  </div>
	    </div>
	  </div>
	  <!-- Controls -->
	  <a class="left carousel-control" href="${pageContext.request.contextPath }/jsp/client/header.jsp#carousel-example-generic" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left"></span>
	  </a>
	  <a class="right carousel-control" href="${pageContext.request.contextPath }/jsp/client/header.jsp#carousel-example-generic" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right"></span>
	  </a>
	</div>
		<br>
	<br>
  </body>
</html>
