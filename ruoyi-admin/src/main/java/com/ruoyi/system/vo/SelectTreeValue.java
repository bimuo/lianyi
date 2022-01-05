package com.ruoyi.system.vo;

import java.util.List;

public class SelectTreeValue {
	
	private Long productId;
	private String label;
	private String value;
	private boolean disabled;
	private List<SelectTreeValue> children;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

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
