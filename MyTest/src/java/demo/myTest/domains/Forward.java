package demo.myTest.domains;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import demo.myTest.vo.ForwardFormBean;

@SuppressWarnings("serial")
public class Forward implements Serializable {
	private String id;
	private String content;
	private Activity act;
	private User createdBy;
	private Date createdAt;
	private int status; // 0 for normal; 1 for disabled
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Activity getAct() {
		return act;
	}
	public void setAct(Activity act) {
		this.act = act;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}else if(!(obj instanceof Forward)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Forward)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(act)
				.append("<--")
				.append(createdBy)
				.toString();
	}
	
	public static Forward from(ForwardFormBean formBean,
			User signInUser){
		if(formBean==null || signInUser==null) return null;
		Forward fwd = new Forward();
		fwd.setContent(formBean.getContent());
		fwd.setCreatedBy(signInUser);
		fwd.setCreatedAt(new Date());
		fwd.setStatus(0);
		return fwd;
	}
}
