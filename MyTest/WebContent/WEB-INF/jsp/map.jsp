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
	href="<c:url value="/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/myTest-base.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/myTest-theme.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/validationEngine.bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/map.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/PIE.htc" />" />
<script type="text/javascript"
	src="<c:url value="/js/jquery.1.7.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/myTest.init.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/lib/OpenLayers.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/myLayerSwitcher.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/myMarker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/myPanZoomBar.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/myMap.js" />"></script>
</head>
<body class="front">
	<jsp:include page="/WEB-INF/jsp/comp/header.jsp">
		<jsp:param name="tab" value="home" />
	</jsp:include>
	<%-- <jsp:include page="/WEB-INF/jsp/comp/side.nav.jsp" /> --%>
	<div id="water-fall-wrapper" class="main-wrapper">
		<jsp:include page="/WEB-INF/jsp/comp/spot.filter.bar.jsp">
			<jsp:param name="filters" value="${filters}" />
			<jsp:param name="viewType" value="mv" />
		</jsp:include>
		<div id="map-view" class="board content-wrapper p-r">
			<div id="explore-map" style="width: 100%; min-height: 300px">
				<script type="text/javascript">
					getMapAndHotSpot("init");
				</script>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		adjustWebWidth();
	</script>
	<!-- <script type="text/javascript">
		$(function() {
			flyZoomBarAndSwitcher();
		});
	</script> -->
	<script type="text/javascript" src="<c:url value="/js/myTest.op.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>

	<div id="markerTemplate" class="hide">
		<ul id="primary">
			<li id="host"><span>户主：</span><span id="content"></span></li>
			<li id="address"><span>住址：</span><span id="content"></span></li>
			<li id="job"><span>家庭主要从事：</span><span id="content"></span></li>
		</ul>
		<ul id="member">
			<li id="relation"><span id="content"></span></li>
			<li id="name"><span id="content"></span></li>
			<li id="job"><span>现从事：</span><span id="content"></span></li>
		</ul>
	</div>
</body>
</html>