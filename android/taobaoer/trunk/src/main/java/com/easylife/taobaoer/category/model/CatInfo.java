package com.easylife.taobaoer.category.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.easylife.taobaoer.core.model.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatInfo extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nid;
	private String word_name;
	private String url;
	private String pic;
	
	
	
	public String getNid() {
		return nid;
	}



	public void setNid(String nid) {
		this.nid = nid;
	}



	public String getWord_name() {
		return word_name;
	}



	public void setWord_name(String word_name) {
		this.word_name = word_name;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getPic() {
		return pic;
	}



	public void setPic(String pic) {
		this.pic = pic;
	}



	@Override
	public Object getIdentify() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
