package demo.myTest.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MapImageBean implements Serializable {
	private String mapId;
	
	private String mapName;
	
	private String mapImageUrl;

	private double[] originImageSize;

	public String getMapImageUrl() {
		return mapImageUrl;
	}

	public void setMapImageUrl(String mapImageUrl) {
		this.mapImageUrl = mapImageUrl;
	}

	public double[] getOriginImageSize() {
		return originImageSize;
	}

	public void setOriginImageSize(double[] originImageSize) {
		this.originImageSize = originImageSize;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

}
