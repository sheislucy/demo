MyMarker = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {

	initialize : function(id, lonlat, contentSize, contentHTML, anchor,
			closeBox, closeBoxCallback, imageUrl) {
		this.imageSrc = imageUrl;
		OpenLayers.Popup.Framed.prototype.initialize.apply(this, arguments);
		this.contentDiv.className = this.contentDisplayClass;
	},

	CLASS_NAME : "MyMarker"
});

MyMarker.prototype.imageSize = new OpenLayers.Size(300, 220);

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
		var position = this.positionBlocks[this.relativePosition];
		var positionBlock = position.blocks[0];

		// adjust sizes
		var l = positionBlock.anchor.left;
		var b = positionBlock.anchor.bottom;
		var r = positionBlock.anchor.right;
		var t = positionBlock.anchor.top;

		var block = this.blocks[0];

		var w = (isNaN(positionBlock.size.w)) ? this.size.w - (r + l)
				: positionBlock.size.w;

		var h = (isNaN(positionBlock.size.h)) ? this.size.h - (b + t)
				: positionBlock.size.h;

		block.div.style.width = (w < 0 ? 0 : w) + 'px';
		block.div.style.height = (h < 0 ? 0 : h) + 'px';

		block.div.style.left = (l != null) ? l + 'px' : '';
		block.div.style.bottom = (b != null) ? b + 'px' : '';
		block.div.style.right = (r != null) ? r + 'px' : '';
		block.div.style.top = (t != null) ? t + 'px' : '';

		block.image.style.left = positionBlock.position.x + 'px';
		block.image.style.top = positionBlock.position.y + 'px';

		this.contentDiv.style.left = this.padding.left + "px";
		this.contentDiv.style.top = this.padding.top + "px";

		var offsetTop = this.div.style.top;
		offsetTop = offsetTop.replace('px', '');
		this.div.style.top = offsetTop - 20 + 'px';
		
		block.image.style.width = block.div.style.width;
		block.image.style.height = block.div.style.height;
		block.image.style.opacity = 0.8;
	}
};

/*
 * MyMarker.prototype.updateSize = function(){ this.setSize(this.contentSize); };
 */
