package com.easylife.taobaoer.category.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.easylife.taobaoer.core.model.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatProduct extends Entity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long twitter_id;
	private long twitter_goods_id;
	private String pic_url;
	private String j_pic_url;
	private String q_pic_url;
	private String title;
	private int pic_width;
	private int pic_height;
	private int count_like;

	public long getTwitter_id() {
		return twitter_id;
	}

	public void setTwitter_id(long twitter_id) {
		this.twitter_id = twitter_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getTwitter_goods_id() {
		return twitter_goods_id;
	}

	public void setTwitter_goods_id(long twitter_goods_id) {
		this.twitter_goods_id = twitter_goods_id;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getJ_pic_url() {
		return j_pic_url;
	}

	public void setJ_pic_url(String j_pic_url) {
		this.j_pic_url = j_pic_url;
	}

	public String getQ_pic_url() {
		return q_pic_url;
	}

	public void setQ_pic_url(String q_pic_url) {
		this.q_pic_url = q_pic_url;
	}

	public int getPic_width() {
		return pic_width;
	}

	public void setPic_width(int pic_width) {
		this.pic_width = pic_width;
	}

	public int getPic_height() {
		return pic_height;
	}

	public void setPic_height(int pic_height) {
		this.pic_height = pic_height;
	}

	public int getCount_like() {
		return count_like;
	}

	public void setCount_like(int count_like) {
		this.count_like = count_like;
	}

	@Override
	public Object getIdentify() {
		return null;
	}

}
