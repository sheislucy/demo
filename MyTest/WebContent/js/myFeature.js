MyFeature = OpenLayers.Class(OpenLayers.Feature, {
	initialize : function(layer, lonlat, data, dbId) {
		this.dbId = dbId;
		OpenLayers.Feature.prototype.initialize.apply(this, arguments);
	},
	CLASS_NAME : "MyFeature"
});