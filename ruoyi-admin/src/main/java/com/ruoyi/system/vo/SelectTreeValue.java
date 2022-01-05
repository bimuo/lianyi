package com.ruoyi.system.vo;

import java.util.List;

public class SelectTreeValue {
	private String label;
	private String value;
	
	private List<SelectTreeValue> children;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<SelectTreeValue> getChildren() {
		return children;
	}

	public void setChildren(List<SelectTreeValue> children) {
		this.children = children;
	}
}
