package com.easylife.taobaoer.product.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.easylife.taobaoer.core.model.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductList extends Entity {
	private static final long serialVersionUID = -4595430288632717446L;
	List<Product> data;

	public List<Product> getData() {
		return data;
	}

	public void setData(List<Product> data) {
		this.data = data;
	}

	@Override
	public Object getIdentify() {
		return null;
	}
}
