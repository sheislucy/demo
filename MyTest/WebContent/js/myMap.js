$(function() {

	var Feature = OpenLayers.Feature.Vector;
	var Geometry = OpenLayers.Geometry;
	var Map = OpenLayers.Map;
	var Image = OpenLayers.Layer.Image;

	// Point feature generator -------------start----------
	var FeatureManager = function() {
	};

	FeatureManager.prototype.genPoints = function(coordinates) {
		var features = new Array();
		for ( var i = 0, len = coordinates.length; i < len; i++) {
			var x = coordinates[i].x;
			var y = coordinates[i].y;
			var feature = new Feature(new Geometry.Point(x, y));
			features.push(feature);
		}
		return features;
	};

	/**
	 * zones:[{ zone:[{x,y}] }]
	 * 
	 * @returns {Array}
	 */
	FeatureManager.prototype.genPolygon = function(zones) {
		var lonlatStr = "POLYGON(";
		for ( var i = 0; i < zones.lentgh; i++) {
			var zone = zones[i];
			lonlatStr += "(";
			for ( var j = 0; j < zone.length; j++) {
				lonlatStr += zone[j].x;
				lonlatStr += " ";
				lonlatStr += zone[j].y;
				if (j != zone.length - 1) {
					lonlatStr += ", ";
				}
			}
			lonlatStr += ")";
			if (i != zone.length - 1) {
				lonlatStr += ", ";
			}
		}
		lonlatStr += ")";
		var polygon = new Feature(Geometry.fromWKT(lonlatStr));
		return polygon;
	};

	// Point feature generator -------------end----------
	var featureMgr = new FeatureManager();

	// map generator-----------start--------------
	var MapManager = function() {

	};

	/**
	 * mapMeta:{ w:"", h:"", mapName:"", mapImageUrl:"", }
	 * 
	 * hotspotMeta:{ points:[{x:"",y:""}], polygons:[] }
	 */
	MapManager.prototype.genMap = function(mapMeta, hotspotMeta) {
		if (this.map) {
			this.map.destroy();
		}
		this.map = new Map('explore-map', {
			projection : "EPSG:3857",
		});
		this.map.layers = new Array();

		var options = {
			numZoomLevels : 6,
		};
		var graphic1 = new Image(mapMeta.mapName, web_context + mapMeta.mapUrl,
				new OpenLayers.Bounds(0, 0, mapMeta.w, mapMeta.h),
				new OpenLayers.Size(mapMeta.w, mapMeta.h), options);
		/*
		 * var graphic2 = new Image('规划平面图', web_context + '/img/Map05.jpg', new
		 * OpenLayers.Bounds(0, 0, w, h), new OpenLayers.Size(w, h), options);
		 * var graphic3 = new Image('结构分析', web_context + '/img/Map06jpg', new
		 * OpenLayers.Bounds(0, 0, w, h), new OpenLayers.Size(w, h), options);
		 */

		var CoordinateTranslator = function() {
			this.coo = {};
		};

		CoordinateTranslator.prototype.transalate = function(minBase_x,
				minBase_y) {
			this.coo = {};
			this.coo.x = minBase_x * mapMeta.w / 903;
			this.coo.y = minBase_y * mapMeta.h / 1277.3080645161292;
			return this.coo;
		};

		var ct = new CoordinateTranslator();

		/*
		 * [ ct.transalate(324.5, 923.065), ct.transalate(433.5625, 673.875),
		 * ct.transalate(553.25, 422.96875) ];
		 */
		var points = new Array();
		if (hotspotMeta.points && hotspotMeta.points.length > 0) {
			for ( var i = 0; i < hotspotMeta.points.length; i++) {
				points.push(ct.transalate(hotspotMeta.points[i].x,
						hotspotMeta.points[i].y));
			}
		}

		var polygons_con = new Array();
		if (hotspotMeta.polygons && hotspotMeta.polygons.length > 0) {
			for ( var i = 0; i < hotspotMeta.polygons.length; i++) {
				// one single polygon here, but can contain holes
				var zones_con = new Array();
				var polygon = hotspotMeta.polygons[i];
				for ( var j = 0; j < polygon.length; j++) {
					// one single zone here
					var coordinates_con = new Array();
					var zones = polygon[j];
					for ( var k = 0; k < zones.length; k++) {
						// one point coordinate here
						coordinates_con.push(ct.transalate(zones[k].x,
								zones[k].y));
					}
					zones_con.push(coordinates_con);
				}
				polygons_con.push(zones_con);
			}
		}
		var polygonFeatures = new Array();
		for ( var i = 0; i < polygons_con.length; i++) {
			polygonFeatures.push(featureMgr.genPolygon(polygons_con[i]));
		}

		var vectorLayer = new OpenLayers.Layer.Vector('pointVector-Map03', {
			styleMap : new OpenLayers.StyleMap({
				"default" : {
					pointRadius : 10,
					strokeWidth : 3,
					srokeColor : '#9C9C9C',
					externalGraphic : web_context + '/img/marker-gold.png',
					graphicXOffset : -10,
					graphicYOffset : -20
				},
				select : {
					pointRadius : 13,
					strokeColor : "yellow",
					strokeWidth : 3
				}
			}),
		});
		vectorLayer.addFeatures(featureMgr.genPoints(points));
		vectorLayer.addFeatures(polygonFeatures);

		this.map.addLayers([ graphic1, vectorLayer ]);
		if (this.map.layers && this.map.layers.length > 1) {
			var switcher = new OpenLayers.Control.LayerSwitcher({
				roundedCorner : true,
				roundedCornerColor : "#ADD8E6",
			});
			this.map.addControl(switcher);
		}

		this.map.zoomToMaxExtent();

		// display coordinate of mouse position-------start------------------
		this.map.addControl(new OpenLayers.Control.MousePosition());
		this.map.events.register("mousemove", this.map, function(e) {
			var position = this.events.getMousePosition(e);
			OpenLayers.Util.getElement("coords").innerHTML = position;
		});
		// display coordinate of mouse position-------end------------------
		// select points
		var selectController = new OpenLayers.Control.SelectFeature(
				vectorLayer, {
					clickout : true,
					toggle : false,
					hover : false
				});
		vectorLayer.events.on({
			'featureselected' : function(evt) {
				var feature = evt.feature;
				var popup = new OpenLayers.Popup.FramedCloud("popup",
						OpenLayers.LonLat.fromString(feature.geometry
								.toShortString()), null,
						"<div style='font-size:.8em'>Feature: " + feature.id
								+ "<img src=\"" + web_context
								+ "/img/icon_app.png" + "\">" + "<br>Summary: "
								+ "新规划" + "</div>", null, false);
				popup.minSize = new OpenLayers.Size(100, 50);
				popup.autoSize = true;
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

		// this.map.addControl(selectController);
		// selectController.activate();
	};

	// map generator-----------end--------------

	var mapManager = new MapManager();

	$(window).resize(function() {
		var map_width = $('#explore-map').innerWidth();
		var map_height = map_width / 2480 * 3508;
		$('#explore-map').height(map_height);
	});

	$(window).resize();
	mapManager.genMap($('#explore-map').innerWidth(), $('#explore-map')
			.innerHeight());

});