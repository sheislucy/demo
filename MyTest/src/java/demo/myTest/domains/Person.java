package demo.myTest.domains;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -7288923350240576719L;
	private String name;
	private String relation;
	private String job;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
