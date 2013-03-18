package demo.myTest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class FilterElementVo implements Serializable {

	private String type;
	private String typeLabel;
	private Object value;
	private String label;
	private List<DropdownItem> items = new ArrayList<DropdownItem>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<DropdownItem> getItems() {
		return items;
	}

	public void setItems(List<DropdownItem> items) {
		this.items = items;
	}

}
