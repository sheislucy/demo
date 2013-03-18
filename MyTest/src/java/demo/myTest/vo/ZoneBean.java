package demo.myTest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ZoneBean implements Serializable {
	private List<PointBean> zones = new ArrayList<PointBean>();

	public List<PointBean> getZones() {
		return zones;
	}

	public void setZones(List<PointBean> zones) {
		this.zones = zones;
	}
}
