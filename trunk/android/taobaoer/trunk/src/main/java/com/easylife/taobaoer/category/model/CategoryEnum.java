package com.easylife.taobaoer.category.model;

import com.easylife.taobaoer.R;

public enum CategoryEnum {

	SHANGYI(0, 827, "上衣", R.drawable.cat_sy), QUNZI(1, 829, "裙子",
			R.drawable.cat_qz), KUZI(2, 831, "裤子", R.drawable.cat_kz), XIEZI(3,
			707, "鞋子", R.drawable.cat_xz), BAOBAO(4, 833, "包包",
			R.drawable.cat_bb), PEISHI(5, 833, "配饰", R.drawable.cat_ps);

	private int id;
	private int typeId;
	private String name;
	private int url;

	private CategoryEnum(int id, int typeId, String name, int url) {
		this.id = id;
		this.typeId = typeId;
		this.name = name;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTypeId() {
		return typeId;
	}

	public int getUrl() {
		return url;
	}

	public static CategoryEnum getCategoryEnum(int id) {
		for (CategoryEnum catEnum : CategoryEnum.values()) {
			if (catEnum.id == id) {
				return catEnum;
			}
		}
		return null;
	}
}
