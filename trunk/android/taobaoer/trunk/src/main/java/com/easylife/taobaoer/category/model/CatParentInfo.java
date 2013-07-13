package com.easylife.taobaoer.category.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.easylife.taobaoer.core.model.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatParentInfo extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long typeId;
	private String typeName;
	private String imgulr;
	
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getImgulr() {
		return imgulr;
	}
	public void setImgulr(String imgulr) {
		this.imgulr = imgulr;
	}
	@Override
	public Object getIdentify() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
