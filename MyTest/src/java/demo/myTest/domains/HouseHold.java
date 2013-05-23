package demo.myTest.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HouseHold implements Serializable {
	private static final long serialVersionUID = -2764518892744813923L;
	private String host;
	private String address;
	private String business;
	private List<Person> members = new ArrayList<Person>();

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public List<Person> getMembers() {
		return members;
	}

	public void setMembers(List<Person> members) {
		this.members = members;
	}

}
