package com.mangolion.epicmangorpg.components;

import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;

public class Drop {
	public Item item;
	public ItemCustom itemCustom;
	public float chance;
	public int amount;
	
	public Drop(Item item, float chance, int num) {
		this.item = item;
		this.chance = chance;
		amount = num;
	}
	
	public Drop(ItemCustom item, float chance) {
		this.itemCustom = item;
		this.chance = chance;
	}

}
