package com.mangolion.epicmangorpg.components;

import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.ItemCustom;

public class Drop {
	public Item item;
	public ItemCustom itemCustom;
	public float chance;
	
	public Drop(Item item, float chance) {
		this.item = item;
		this.chance = chance;
	}
	
	public Drop(ItemCustom item, float chance) {
		this.itemCustom = item;
		this.chance = chance;
	}

}
