package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.items.Item.Type;
import com.mangolion.epicmangorpg.items.ItemBuff.Subtype;

public class ItemStat extends Item{

	public float value;
	
	public ItemStat( String name, String desc, Type type, float value, int maxStack, float price) {
		super( name, desc, type, maxStack, price);
		this.value = value;
	}
	
	@Override
	public void onUse(Character character, Character target) {
		/*if (type == Type.Damage)
			target.setDamage(character, value);*/
		if (type == Type.Heal)
			target.setHeal(character, value);
		super.onUse(character, target);
	}
	

}
