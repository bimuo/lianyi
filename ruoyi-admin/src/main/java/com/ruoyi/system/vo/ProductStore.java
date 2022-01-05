package com.ruoyi.system.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品库存
 * @author maliang
 *
 */
public class ProductStore {
	private Long id;
	private String name;
	
	private int count;
	private long productId;
	private long shelvesId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private List<ProductStore> children=new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ProductStore> getChildren() {
		return children;
	}

	public void setChildren(List<ProductStore> children) {
		this.children = children;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getShelvesId() {
		return shelvesId;
	}

	public void setShelvesId(long shelvesId) {
		this.shelvesId = shelvesId;
	}
	
}
