package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.armor.LeatherGloves;
import com.mangolion.epicmangorpg.armor.LeatherHat;
import com.mangolion.epicmangorpg.armor.LeatherJacket;
import com.mangolion.epicmangorpg.armor.LeatherPants;
import com.mangolion.epicmangorpg.armor.LeatherShoes;
import com.mangolion.epicmangorpg.armor.MetalBoots;
import com.mangolion.epicmangorpg.armor.MetalGuantlet;
import com.mangolion.epicmangorpg.armor.MetalHelm;
import com.mangolion.epicmangorpg.armor.PlateArmorLower;
import com.mangolion.epicmangorpg.armor.PlateArmorUpper;
import com.mangolion.epicmangorpg.weapons.WeaponCopperDagger;
import com.mangolion.epicmangorpg.weapons.WeaponGreatCopperSword;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;

public class Items {
	public static Item potionSmall = new ItemPotionSmall(),
			all[] = {potionSmall};
	public static ItemCustom customs[] = {new WeaponCopperDagger(), new WeaponGreatCopperSword(), new WeaponLongSword(), new LeatherGloves(), new LeatherHat(), new LeatherJacket(), new LeatherPants(), new LeatherShoes(), new MetalBoots(), new MetalHelm(),
		new MetalGuantlet(), new PlateArmorLower(), new PlateArmorUpper()};
	
	public static Item getItem(String name){
		for (Item item: all){
			if (item.name.toLowerCase().equals(name.toLowerCase()))
				return item;
		}
		return null;
	}
	
	public static ItemCustom getItemCustom(String name){
		for (ItemCustom item: customs){
			if (item.name.toLowerCase().equals(name.toLowerCase()))
				return item;
		}
		return null;
	}
}
