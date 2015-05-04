package com.mangolion.epicmangorpg.shop;

public abstract class ShopItem<T> {
	public String name, desc;
	public T item;
	public ShopItem(String name, String desc, T item) {
		this.name = name;
		this.desc = desc;
		this.item = item;
	}
	
	public T getItem(){
		return item;
	}
}
