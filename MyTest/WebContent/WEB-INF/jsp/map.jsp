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
	href="<c:url value="/css/myMap.css" />" />
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
			<jsp:param name="viewType" value="mv" />
		</jsp:include>
		<div id="map-view" class="board content-wrapper p-r">
			<div id="explore-map" style="width: 100%; min-height: 600px"></div>
			<c:if test="${not empty mapMeta}">
				<script>
					alert("${mapMeta.mapName}");
				</script>
				<script type="text/javascript" src="<c:url value="/js/myMap.js" />"></script>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">
		adjustWebWidth();
	</script>
	<script type="text/javascript"
		src="<c:url value="/js/lib/OpenLayers.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/lib/Rico/Corner.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/myTest.op.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script>
	<script type="text/javascript"
		src="http://maps.google.com/maps/api/js?sensor=false&language=zh_cn"></script>
	<script type="text/javascript" src="<c:url value="/js/gmap3.js" />"></script>
	<%-- <script type="text/javascript" src="<c:url value="/js/marker.overlay.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/marker.overlay.manager.js" />"></script> --%>
	<script type="text/javascript">
		$(function() {
			var mapManager = new MapManager();

			$(window).resize(function() {
				var map_width = $('#explore-map').innerWidth();
				var map_height = map_width / 2480 * 3508;
				$('#explore-map').height(map_height);
			});

			$(window).resize();

			var mapMeta = {};
			mapMeta.w = "${mapMeta.originImageSize[0]}";
			mapMeta.h = "${mapMeta.originImageSize[1]}";
			mapMeta.mapName = "${mapMeta.mapName}";
			mapMeta.mapImageutl = "${mapMeta.mapImageUrl}";

			var hotspotMeta = {};
			hotspotMeta.points = new Array();
			for ( var i = 0; i < ${'points'}.length; i++) {
				hotspotMeta.points.push({
					x : ${'points'}[i].xAxis,
					y : ${'points'}[i].yAxis
				});
			}

			hotspotMeta.polygons = new Array();
			for ( var i = 0; i < ${'polygons'}.length; i++) {
				var poly_org = ${'polygons'}[i];
				var zones = new Array();
				for ( var j = 0; j < poly_org.polygon.length; j++) {
					var zones_org = poly_org.polygon[j];
					var points = new Array();
					for ( var k = 0; k < zones_org.length; k++) {
						points.push({
							x : zones_org[k].xAxis,
							y : zones_org[k].yAxis
						});
					}
					zones.push(points);
				}
				hotspotMeta.polygons.push(zones);
			}

			mapManager.genMap(mapMeta, hotspotMeta);
		});
	</script>
	<script type="text/javascript" src="<c:url value="/js/ga.js" />"></script>
</body>
</html>