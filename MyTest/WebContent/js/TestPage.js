function init() {
	var map = new OpenLayers.Map('testMap');
	var options = {
		numZoomLevels : 3
	};

	var graphic = new OpenLayers.Layer.Image('Test Image',
			'../img/0911230123.jpg', new OpenLayers.Bounds(-180, -88.759, 180,
					88.759), new OpenLayers.Size(240, 320), options);
	var graphic2 = new OpenLayers.Layer.Image("Test Image2",
			'../img/200911111439323.jpg', new OpenLayers.Bounds(-180, -88.759,
					180, 88.759), new OpenLayers.Size(240, 320), options);

	map.addLayers([ graphic, graphic2 ]);
	map.addControl(new OpenLayers.Control.LayerSwitcher());
	map.zoomToMaxExtent();
}

