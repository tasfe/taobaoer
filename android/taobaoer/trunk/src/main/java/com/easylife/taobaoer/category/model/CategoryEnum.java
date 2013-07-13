package com.easylife.taobaoer.category.model;

public enum CategoryEnum {

	SHANGYI(0, 827, "上衣"), QUNZI(1, 829, "裙子"), KUZI(2, 831, "裤子"), XIEZI(
			3, 707, "鞋子"), BAOBAO(4, 833, "包包"), PEISHI(5, 833, "配饰");

	private int id;
	private int typeId;
	private String name;

	private CategoryEnum(int id, int typeId, String name) {
		this.id = id;
		this.typeId = typeId;
		this.name = name;
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

	public static CategoryEnum getCategoryEnum(int id) {
		for (CategoryEnum catEnum : CategoryEnum.values()) {
			if (catEnum.id == id) {
				return catEnum;
			}
		}
		return null;
	}
}
