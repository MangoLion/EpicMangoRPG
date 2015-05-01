package com.mangolion.epicmangorpg.items;

import com.mangolion.epicmangorpg.armor.ClothRobe;
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
import com.mangolion.epicmangorpg.items.Item.Type;
import com.mangolion.epicmangorpg.weapons.BasicMachinegun;
import com.mangolion.epicmangorpg.weapons.BasicRifle;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.CopperDagger;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.SniperRifle;

public class Items {
	public static Item potionSmall = new ItemPotionSmall(),
			arrow = new ItemArrow(),
			bullet = new Item("Bullet", "", Type.Ammunition, 100){},
			crystalWater = new Item("Water Crystal", "", Type.Ammunition, 100){},
			crystalFire = new Item("Fire Crystal", "", Type.Ammunition, 100){},
			crystalWind = new Item("Wind Crystal", "", Type.Ammunition, 100){},
			crystalEarth = new Item("Earth Crystal", "", Type.Ammunition, 100){},
			all[] = {potionSmall, arrow, bullet, crystalWater, crystalFire, crystalWind, crystalEarth};
	public static ItemCustom customs[] = {new CopperDagger(), new GreatCopperSword(), new LongSword(), new LeatherGloves(), new LeatherHat(), new LeatherJacket(), new LeatherPants(), new LeatherShoes(), new MetalBoots(), new MetalHelm(),
		new MetalGuantlet(), new PlateArmorLower(), new PlateArmorUpper(),  new ClothRobe(), new BowShort(), new BasicRifle(), new SniperRifle(), new BasicMachinegun(), new CylinderBasic()};
	
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
