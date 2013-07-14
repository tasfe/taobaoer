package com.easylife.taobaoer.detail.model;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsInfo {
	public Goods  goods;
	public String  remark;
	public String  pic_url;
	public String  pic_width;
	public String  twitter_id;

	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getPic_width() {
		return pic_width;
	}
	public void setPic_width(String pic_width) {
		this.pic_width = pic_width;
	}
	public String getTwitter_id() {
		return twitter_id;
	}
	public void setTwitter_id(String twitter_id) {
		this.twitter_id = twitter_id;
	}
	
}
