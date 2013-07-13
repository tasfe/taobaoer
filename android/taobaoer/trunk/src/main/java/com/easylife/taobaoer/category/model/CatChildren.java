package com.easylife.taobaoer.category.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.easylife.taobaoer.core.model.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatChildren extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CatInfo info;
	
	private List<Object> children;

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

	public CatInfo getInfo() {
		return info;
	}

	public void setInfo(CatInfo info) {
		this.info = info;
	}

	@Override
	public Object getIdentify() {
		// TODO Auto-generated method stub
		return null;
	}

}
