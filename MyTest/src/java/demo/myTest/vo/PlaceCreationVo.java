package demo.myTest.vo;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class PlaceCreationVo implements Serializable {
	
	private Map<String, String> address;
	private String fullAddr;
	private Double[] lngLat;
	
	public Map<String, String> getAddress() {
		return address;
	}
	public void setAddress(Map<String, String> address) {
		this.address = address;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
	public Double[] getLngLat() {
		return lngLat;
	}
	public void setLngLat(Double[] lngLat) {
		this.lngLat = lngLat;
	}
}
