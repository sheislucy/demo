package demo.myTest.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ForwardFormBean implements Serializable {

	private String actId;
	private String content;
	
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
