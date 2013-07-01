package com.easylife.taobaoer.core.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Code extends Entity {
	private static final long serialVersionUID = 6365758460700406811L;
	private String code;
	private long expiration;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	@Override
	public Object getIdentify() {
		return null;
	}

}
