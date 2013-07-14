package com.easylife.taobaoer.detail.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsDetail {
		public GoodsInfo  data;
		public String r;
	
		public GoodsInfo getData() {
			return data;
		}
		public void setData(GoodsInfo data) {
			this.data = data;
		}
		
		public String getR() {
			return r;
		}
		public void setR(String r) {
			this.r = r;
		}	
}
