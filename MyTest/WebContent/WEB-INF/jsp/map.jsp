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
			<div id="explore-map" style="width:100%; min-height: 600px">
				<!--	<div id="map-loading" class="p-a z-99 dis-n" style="top: 250px; width:100%">
					<div class="bg-black ta-c br-5 c-eee fs-16 fw-b p-15" 
						style="width:48px; height:48px; margin: 0 auto;">
						<p class="bg-big-loading-white" style="width:48px; height:48px;"></p>
					</div>
				</div>-->
			</div>


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

			var Feature = OpenLayers.Feature.Vector;
			var Geometry = OpenLayers.Geometry;
			var Map = OpenLayers.Map;
			var Image = OpenLayers.Layer.Image;
			var Rule = OpenLayers.Rule;
			var Filter = OpenLayers.Filter;
			var Vector = OpenLayers.Layer.Vector;
			var Layer = OpenLayers.Layer;

			var MapManager = function() {

			};
			MapManager.prototype.genMap = function(w, h) {
				if (!this.map) {
					this.map = new Map('explore-map', {
						projection : "EPSG:3857"
					});
					var options = {
						numZoomLevels : 6,
					};
					var switcher = new OpenLayers.Control.LayerSwitcher({
						roundedCorner : true,
						roundedCornerColor : "#87CEFA",
					});
					this.map.addControl(switcher);
				}
				/* var graphic2 = new Image('规划平面图', web_context + '/img/Map05.jpg',
						new OpenLayers.Bounds(-1, -1, 1, 1),
						new OpenLayers.Size(3508, 2480), options);

				var graphic3 = new Image('结构分析', web_context + '/img/Map06.jpg',
						new OpenLayers.Bounds(-1, -1, 1, 1),
						new OpenLayers.Size(3508, 2480), options); */
				var layers = this.map.getLayersByName('土地利用总图');
				if(layers && layers.length != 0){
					this.map.removeLayer(layers[0]);
				}
				var graphic1 = new Image('土地利用总图', web_context
						+ '/img/Map03.jpg', new OpenLayers.Bounds(0, 0, w, h),
						new OpenLayers.Size(w, h), options);
				this.map.addLayers([ graphic1 ]);
				this.map.zoomToMaxExtent();
			};

			var mapManager = new MapManager();

			
			$(window).resize(function() {
				var map_width = $('#explore-map').innerWidth();
				var map_height= map_width/0.707;
				$('#explore-map').height(map_height);
				mapManager.genMap(map_width, map_height);
			});

			$(window).resize();

		});
	</script>
	<script type="text/javascript" src="<c:url value="/js/ga.js" />"></script>
</body>
</html>