package demo.myTest.domains;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class Resource implements Serializable {

	private String id;
	private String resId;
	private String ext;
	private Integer[] orgSize;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public Integer[] getOrgSize() {
		return orgSize;
	}
	public void setOrgSize(Integer[] orgSize) {
		this.orgSize = orgSize;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
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
		}else if(!(obj instanceof Resource)){
			return false;
		}
		return new EqualsBuilder()
				.append(id, ((Resource)obj).getId())
				.isEquals();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(resId)
				.toString();
	}
}