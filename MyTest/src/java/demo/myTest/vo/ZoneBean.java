package demo.myTest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ZoneBean implements Serializable {
	private List<PointBean> zone = new ArrayList<PointBean>();

	public List<PointBean> getZone() {
		return zone;
	}

	public void setZone(List<PointBean> zone) {
		this.zone = zone;
	}

}
