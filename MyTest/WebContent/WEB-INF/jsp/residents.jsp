<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Demo of XXX</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/myResidents.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/myTest-base.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/myTest-theme.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/validationEngine.bootstrap.css" />" />
<script type="text/javascript"
	src="<c:url value="/js/jquery.1.7.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/myTest.init.js" />"></script>
</head>
<body class="front">
	<jsp:include page="/WEB-INF/jsp/comp/header.jsp">
		<jsp:param name="tab" value="home" />
	</jsp:include>
	<%-- <jsp:include page="/WEB-INF/jsp/comp/side.nav.jsp" /> --%>
	<div id="water-fall-wrapper" class="main-wrapper">
		<jsp:include page="/WEB-INF/jsp/comp/spot.filter.bar.jsp">
			<jsp:param name="filters" value="${filters}" />
			<jsp:param name="viewType" value="ri" />
		</jsp:include>
		<div id="map-view" class="board content-wrapper p-r">
			<div id="resident-list" style="width: 100%; min-height: 600px">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span2">
							<div class="well sidebar-nav">
								<ul class="nav nav-list">
									<li class="nav-header">Sidebar</li>
									<li class="active"><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li class="nav-header">Sidebar</li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li class="nav-header">Sidebar</li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
									<li><a href="#">Link</a></li>
								</ul>
							</div>
							<!--/.well -->
						</div>
						<div class="span10">
							<div class="hero-unit">
								<h1>Hello, world!</h1>
								<p>This is a template for a simple marketing or
									informational website. It includes a large callout called the
									hero unit and three supporting pieces of content. Use it as a
									starting point to create something more unique.</p>
								<p>
									<a href="#" class="btn btn-primary btn-large">Learn more
										&raquo;</a>
								</p>
							</div>
							<div class="row-fluid">
								<div class="span4">
									<h2>Heading</h2>
									<p>Donec id elit non mi porta gravida at eget metus. Fusce
										dapibus, tellus ac cursus commodo, tortor mauris condimentum
										nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
										malesuada magna mollis euismod. Donec sed odio dui.</p>
									<p>
										<a class="btn" href="#">View details &raquo;</a>
									</p>
								</div>
								<!--/span-->
								<div class="span4">
									<h2>Heading</h2>
									<p>Donec id elit non mi porta gravida at eget metus. Fusce
										dapibus, tellus ac cursus commodo, tortor mauris condimentum
										nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
										malesuada magna mollis euismod. Donec sed odio dui.</p>
									<p>
										<a class="btn" href="#">View details &raquo;</a>
									</p>
								</div>
								<!--/span-->
								<div class="span4">
									<h2>Heading</h2>
									<p>Donec id elit non mi porta gravida at eget metus. Fusce
										dapibus, tellus ac cursus commodo, tortor mauris condimentum
										nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
										malesuada magna mollis euismod. Donec sed odio dui.</p>
									<p>
										<a class="btn" href="#">View details &raquo;</a>
									</p>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
							<div class="row-fluid">
								<div class="span4">
									<h2>Heading</h2>
									<p>Donec id elit non mi porta gravida at eget metus. Fusce
										dapibus, tellus ac cursus commodo, tortor mauris condimentum
										nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
										malesuada magna mollis euismod. Donec sed odio dui.</p>
									<p>
										<a class="btn" href="#">View details &raquo;</a>
									</p>
								</div>
								<!--/span-->
								<div class="span4">
									<h2>Heading</h2>
									<p>Donec id elit non mi porta gravida at eget metus. Fusce
										dapibus, tellus ac cursus commodo, tortor mauris condimentum
										nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
										malesuada magna mollis euismod. Donec sed odio dui.</p>
									<p>
										<a class="btn" href="#">View details &raquo;</a>
									</p>
								</div>
								<!--/span-->
								<div class="span4">
									<h2>Heading</h2>
									<p>Donec id elit non mi porta gravida at eget metus. Fusce
										dapibus, tellus ac cursus commodo, tortor mauris condimentum
										nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
										malesuada magna mollis euismod. Donec sed odio dui.</p>
									<p>
										<a class="btn" href="#">View details &raquo;</a>
									</p>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		adjustWebWidth();
	</script>
	<script type="text/javascript" src="<c:url value="/js/myTest.op.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
</body>
</html>