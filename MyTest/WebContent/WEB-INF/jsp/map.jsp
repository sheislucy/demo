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
			<div id="explore-map" style="width: 100%; min-height: 600px">
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

			//Point feature generator -------------start----------
			var FeatureManager = function() {
			};

			FeatureManager.prototype.genFeature = function(coordinate) {
				this.features = new Array();
				for ( var i = 0, len = coordinate.length; i < len; i++) {
					var x = coordinate[i].x;
					var y = coordinate[i].y;
					var feature = new Feature(new Geometry.Point(x, y));
					this.features[i] = feature;
				}
			};

			//Point feature generator -------------end----------
			var featureMgr = new FeatureManager();

			// map generator-----------start--------------
			var MapManager = function() {

			};
			MapManager.prototype.genMap = function(w, h) {
				if (!this.map) {
					this.map = new Map('explore-map', {
					//center: new OpenLayers.LonLat(0, 0)
					});
				}
				this.map.layers = new Array();

				var options = {
					numZoomLevels : 6,
				};
				var graphic1 = new Image('土地利用总图', web_context
						+ '/img/Map03.jpg', new OpenLayers.Bounds(0, 0, w, h),
						new OpenLayers.Size(w, h), options);
				var graphic2 = new Image('规划平面图', web_context
						+ '/img/Map05.jpg', new OpenLayers.Bounds(0, 0, w, h),
						new OpenLayers.Size(w, h), options);
				var graphic3 = new Image('结构分析', web_context + '/img/Map06jpg',
						new OpenLayers.Bounds(0, 0, w, h), new OpenLayers.Size(
								w, h), options);

				var coordinate = [ {
					x : 326.25,
					y : 933.96906
				}, {
					x : 431.25,
					y : 680.96906
				}, {
					x : 551.25,
					y : 423.96906
				} ];
				featureMgr.genFeature(coordinate);

				var vectorLayer = new OpenLayers.Layer.Vector(
						'pointVector-Map03', {
							styleMap : new OpenLayers.StyleMap({
								"default" : {
									pointRadius : 10,
									strokeWidth : 3,
									srokeColor : '#9C9C9C',
									externalGraphic : web_context
											+ '/img/marker-gold.png'
								},
								select : {
									pointRadius : 13,
									strokeColor : "yellow",
									strokeWidth : 3
								}
							}),
						});
				vectorLayer.addFeatures(featureMgr.features);

				this.map
						.addLayers([ graphic1, graphic2, graphic3, vectorLayer ]);
				if (this.map.layers && this.map.layers.length > 1) {
					var switcher = new OpenLayers.Control.LayerSwitcher({
						roundedCorner : true,
						roundedCornerColor : "#ADD8E6",
					});
					this.map.addControl(switcher);
				}

				this.map.zoomToMaxExtent();

				//display coordinate of mouse position-------start------------------
				/* this.map.addControl(new OpenLayers.Control.MousePosition());
				this.map.events.register("mousemove", this.map, function(e) {
					var position = this.events.getMousePosition(e);
					OpenLayers.Util.getElement("coords").innerHTML = position;
				}); */
				//display coordinate of mouse position-------end------------------
				//select points
				var selectController = new OpenLayers.Control.SelectFeature(
						vectorLayer, {
							clickout : true,
							toggle : false,
							hover : false
						});
				vectorLayer.events
						.on({
							'featureselected' : function(evt) {
								var feature = evt.feature;
								var popup = new OpenLayers.Popup.FramedCloud(
										"popup",
										OpenLayers.LonLat
												.fromString(feature.geometry
														.toShortString()),
										null,
										"<div style='font-size:.8em'>Feature: "
												+ feature.id
												+ "<img src=\"" + web_context + "/img/icon_app.png" + "\">"
												+ "<br>Summary: " + "新规划"
												+ "</div>", null, false);
								popup.minSize = new OpenLayers.Size(100, 50);
								popup. = true;
								feature.popup = popup;
								this.map.addPopup(popup);
							},
							'featureunselected' : function(evt) {
								var feature = evt.feature;
								this.map.removePopup(feature.popup);
								feature.popup.destroy();
								feature.popup = null;
							}
						});

				this.map.addControl(selectController);
				selectController.activate();
			};

			// map generator-----------end--------------

			var mapManager = new MapManager();

			$(window).resize(function() {
				var map_width = $('#explore-map').innerWidth();
				var map_height = map_width / 0.707;
				$('#explore-map').height(map_height);
				mapManager.genMap(map_width, map_height);

			});

			$(window).resize();

		});
	</script>
	<script type="text/javascript" src="<c:url value="/js/ga.js" />"></script>
</body>
</html>