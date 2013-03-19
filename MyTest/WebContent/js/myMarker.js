MyMarker = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {

	initialize : function(id, lonlat, contentSize, contentHTML, anchor,
			closeBox, closeBoxCallback, imageUrl) {
		this.imageSrc = OpenLayers.Util.getImageLocation(imageUrl);
		OpenLayers.Popup.Framed.prototype.initialize.apply(this, arguments);
		this.contentDiv.className = this.contentDisplayClass;
	},

	CLASS_NAME : "MyMarker"
});

MyMarker.prototype.createBlocks = function() {
	this.blocks = [];
	var block = {};
	var divId = this.id + '_FrameDecorationDiv';
	block.div = OpenLayers.Util.createDiv(divId, null, null, null, "absolute",
			null, "hidden", null);
	var imgId = this.id + '_FrameDecorationImg';

	block.image = OpenLayers.Util.createImage(imgId, null, this.imageSize,
			this.imageSrc, "absolute", null, null, null);

	block.div.appendChild(block.image);
	this.blocks.push(block);
	this.groupDiv.appendChild(block.div);
};

MyMarker.prototype.updateBlocks = function() {
	if (!this.blocks) {
		this.createBlocks();
	}

	if (this.size && this.relativePosition) {
		var block = this.blocks[0];
		
		block.div.style.width = this.size.w + 'px';
        block.div.style.height = this.size.h + 'px';

		this.contentDiv.style.left = this.padding.left + "px";
        this.contentDiv.style.top = this.padding.top + "px";
	}
};