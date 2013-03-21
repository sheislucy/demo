var Feature = OpenLayers.Feature.Vector;
var Geometry = OpenLayers.Geometry;
var Map = OpenLayers.Map;
var Image = OpenLayers.Layer.Image;
var Rule = OpenLayers.Rule;
var Filter = OpenLayers.Filter;

// Point feature generator -------------start----------
var FeatureManager = function() {
};

FeatureManager.prototype.genPoints = function(coordinates) {
	var features = new Array();
	for ( var i = 0, len = coordinates.length; i < len; i++) {
		var x = coordinates[i].x;
		var y = coordinates[i].y;
		var feature = new Feature(new Geometry.Point(x, y), {
			styleClass : "pointDefault",
			dbFeatureId : coordinates[i].featureId
		});
		features.push(feature);
	}
	return features;
};

/**
 * zones:[{ zone:[{x,y}] }]
 * 
 * @returns {Array}
 */
FeatureManager.prototype.genPolygon = function(zones, featureId) {
	var lonlatStr = "POLYGON(";
	for ( var i = 0; i < zones.length; i++) {
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
		if (i != zones.length - 1) {
			lonlatStr += ", ";
		}
	}
	lonlatStr += ")";
	var polygon = new Feature(Geometry.fromWKT(lonlatStr), {
		styleClass : "zoneDefault",
		dbFeatureId : featureId
	});
	return polygon;
};

// Point feature generator -------------end----------
var featureMgr = new FeatureManager();

// map generator-----------start--------------
var MapManager = function() {

};

/**
 * mapMeta:{ width:"", height:"", mapName:"", mapImageUrl:"", }
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
	this.dbMapId = mapMeta.mapId;
	this.map.layers = new Array();

	var options = {
		numZoomLevels : 6,
	};
	var graphic1 = new Image(mapMeta.mapName,
			web_context + mapMeta.mapImageUrl, new OpenLayers.Bounds(0, 0,
					mapMeta.view_width, mapMeta.view_height),
			new OpenLayers.Size(mapMeta.view_width, mapMeta.view_height),
			options);

	var CoordinateTranslator = function() {
		this.coo = {};
	};

	CoordinateTranslator.prototype.translate = function(minBase_x, minBase_y) {
		this.coo = {};
		this.coo.x = minBase_x * mapMeta.view_width / 903; // minimum width of
		// div#explore-map
		this.coo.y = minBase_y * mapMeta.view_height / 1277.3080645161292; // minimum
		// height
		// of
		// div#explore-map
		return this.coo;
	};

	var ct = new CoordinateTranslator();

	var points = new Array();
	if (hotspotMeta.points && hotspotMeta.points.length > 0) {
		for ( var i = 0; i < hotspotMeta.points.length; i++) {
			var point = ct.translate(hotspotMeta.points[i].x,
					hotspotMeta.points[i].y);
			point.featureId = hotspotMeta.points[i].featureId;
			points.push(point);
		}
	}

	var polygons_con = new Array();
	if (hotspotMeta.polygons && hotspotMeta.polygons.length > 0) {
		for ( var i = 0; i < hotspotMeta.polygons.length; i++) {
			// one single polygon here, but can contain holes
			var zones_con = new Array();
			var polygon = hotspotMeta.polygons[i];
			for ( var j = 0; j < polygon.polygon.length; j++) {
				// one single zone here
				var coordinates_con = new Array();
				var zones = polygon.polygon[j];
				for ( var k = 0; k < zones.zones.length; k++) {
					// one point coordinate here
					coordinates_con.push(ct.translate(zones.zones[k].x,
							zones.zones[k].y));
				}
				zones_con.push(coordinates_con);
			}
			zones_con.featureId = polygon.featureId;
			polygons_con.push(zones_con);
		}
	}
	var polygonFeatures = new Array();
	for ( var i = 0; i < polygons_con.length; i++) {
		polygonFeatures.push(featureMgr.genPolygon(polygons_con[i],
				polygons_con[i].featureId));
	}

	var defaultStyle = new OpenLayers.Style({
		pointRadius : 10,
		strokeWidth : 3,
		strokeOpacity : 0.7,
		strokeColor : "navy",
		fillColor : "#ffcc66",
		fillOpacity : 1
	}, {
		rules : [ new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "pointDefault"
			}),
			symbolizer : {
				pointRadius : 20,
				strokeWidth : 2,
				srokeColor : '#9C9C9C',
				externalGraphic : web_context + '/img/material/a17.png',
				graphicXOffset : -2,
				graphicYOffset : -38
			}
		}), new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "zoneDefault"
			}),
			symbolizer : {
				strokeWidth : 2,
				strokeOpacity : 0.7,
				strokeColor : "navy",
				fillColor : "#5571DD",
				fillOpacity : 0.5
			}
		}) ]
	});

	var selectStyle = new OpenLayers.Style({
		fillColor : "red",
		pointRadius : 13,
		strokeColor : "yellow",
		strokeWidth : 3
	}, {
		rules : [ new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "pointDefault"
			}),
			symbolizer : {
				pointRadius : 24,
				strokeWidth : 2,
				srokeColor : '#9C9C9C',
				externalGraphic : web_context + '/img/material/a14.png',
				graphicXOffset : -2,
				graphicYOffset : -45
			}
		}), new Rule({
			filter : new Filter.Comparison({
				type : "==",
				property : "styleClass",
				value : "zoneDefault"
			}),
			symbolizer : {
				strokeWidth : 2,
				strokeOpacity : 0.7,
				strokeColor : "navy",
				fillColor : "#ED57B1",
				fillOpacity : 0.6
			}
		}) ]
	});

	var vectorLayer = new OpenLayers.Layer.Vector(mapMeta.mapName, {
		styleMap : new OpenLayers.StyleMap({
			"default" : defaultStyle,
			"select" : selectStyle
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
	/*
	 * this.map.addControl(new OpenLayers.Control.MousePosition());
	 * this.map.events.register("mousemove", this.map, function(e) { var
	 * position = this.events.getMousePosition(e);
	 * OpenLayers.Util.getElement("coords").innerHTML = position; });
	 */
	// display coordinate of mouse position-------end------------------
	// select points
	var selectController = new OpenLayers.Control.SelectFeature(vectorLayer, {
		clickout : true,
		toggle : false,
		hover : false
	});
	vectorLayer.events.on({
		'featureselected' : showMarker,
		'featureunselected' : hideMarker
	});

	this.map.addControl(selectController);
	this.map.addControl(new OpenLayers.Control.MousePosition());
	this.map.events.register("mousemove", this.map, function(e) {
		mouseLonlat = e.object.getLonLatFromPixel(e.xy);
	});
	selectController.activate();
};

var mouseLonlat = {};

var hideMarker = function(evt) {
	var feature = evt.feature;
	this.map.removePopup(feature.popup);
	feature.popup.destroy();
	feature.popup = null;
};

var showMarker = function(evt) {
	var feature = evt.feature;
	var lonlat;
	var mouseLonlatOnClick = mouseLonlat;
	if (feature.geometry instanceof Geometry.Point) {
		lonlat = OpenLayers.LonLat.fromString(feature.geometry.toShortString());
	} else {
		lonlat = new OpenLayers.LonLat(mouseLonlatOnClick.lon,
				mouseLonlatOnClick.lat);
	}
	var map = this.map;
	$.getJSON(web_context + '/map/' + "map03" + "/feature/"
			+ feature.data.dbFeatureId + "/marker", function(data) {
		if (data && data.resultCode == 'SUCCESS') {
			var popup = new MyMarker("myPopup", lonlat, new OpenLayers.Size(
					260, 180), data.resultData, null, false, null, web_context
					+ "/img/material/marker02.png");
			popup.minSize = new OpenLayers.Size(260, 180);
			popup.autoSize = true;
			feature.popup = popup;
			map.addPopup(popup);
		}
	});
};

// map generator-----------end--------------
var getMapAndHotSpot = function(mapId) {
	$.getJSON(web_context + '/map/' + mapId, function(data) {
		if (data && data.resultCode == 'SUCCESS') {
			$('#explore-map').html("");
			var hotspotMeta = {};
			var mapMeta = data.resultData.mapMeta;
			hotspotMeta.points = data.resultData.points;
			hotspotMeta.polygons = data.resultData.polygons;

			var map_width = $('#explore-map').innerWidth();
			var map_height = map_width / mapMeta.width * mapMeta.height;
			$('#explore-map').height(map_height);
			mapMeta.view_width = $('#explore-map').innerWidth();
			mapMeta.view_height = $('#explore-map').innerHeight();
			var mapManager = new MapManager();
			mapManager.genMap(mapMeta, hotspotMeta);
		}
	});
};
