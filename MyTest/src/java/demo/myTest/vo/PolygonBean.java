package demo.myTest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PolygonBean implements Serializable {
	private List<ZoneBean> polygon = new ArrayList<ZoneBean>();

	public List<ZoneBean> getPolygon() {
		return polygon;
	}

	public void setPolygon(List<ZoneBean> polygon) {
		this.polygon = polygon;
	}

}
