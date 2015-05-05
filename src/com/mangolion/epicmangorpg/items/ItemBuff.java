package com.mangolion.epicmangorpg.items;

public class ItemBuff extends Item{

	public enum Subtype{
		hp,
		mp,
		sp,
		bal,
		dex,
		inte,
		agi,
		str
	}
	
	public float value;
	
	public ItemBuff( String name, String desc, Subtype type, float value, int maxStack, float price) {
		super(name, desc, Type.ChangeStat, maxStack, price);
		// TODO Auto-generated constructor stub
	}

}
