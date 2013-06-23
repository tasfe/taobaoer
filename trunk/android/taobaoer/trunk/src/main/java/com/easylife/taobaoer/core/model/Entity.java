package com.easylife.taobaoer.core.model;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {

	private static final long serialVersionUID = -6050150149915565832L;

	public abstract Object getIdentify();
}
