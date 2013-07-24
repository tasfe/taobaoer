package com.easylife.taobaoer.category.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.easylife.taobaoer.core.model.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatProductList extends Entity{

	private static final long serialVersionUID = 1772328161837469902L;
	private List<CatProduct> data;
	private String r;
	
	public List<CatProduct> getData() {
		return data;
	}
	public void setData(List<CatProduct> data) {
		this.data = data;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	
	@Override
	public Object getIdentify() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
