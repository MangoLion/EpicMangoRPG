package com.mangolion.epicmangorpg.items;

public class ItemPotionSmall extends ItemStat{

	public ItemPotionSmall() {
		super( "Small Potion", "", Type.Heal, 50, 10, 4);
		setConsumable(0.2f, 0.1f, 0, true);
	}

}
