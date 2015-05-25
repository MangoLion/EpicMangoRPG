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
import com.mangolion.epicmangorpg.weapons.RifleAssault;
import com.mangolion.epicmangorpg.weapons.BasicMachinegun;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.CopperDagger;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.SniperRifle;
import com.mangolion.epicmangorpg.weapons.WandPine;

public class Items {
	public static Item potionSmall = new ItemPotionSmall(),
			arrow = new ItemArrow(),
			bullet = new Item("Bullet", "", Type.Ammunition, 100, 0.1f){},
			grenadeAmmo = new Item("Refile Grenade", "", Type.Ammunition, 100, 0.3f){},
			crystalWater = new Item("Water Crystal", "", Type.Ammunition, 100, 0.1f){},
			crystalFire = new Item("Fire Crystal", "", Type.Ammunition, 100, 0.1f){},
			crystalWind = new Item("Wind Crystal", "", Type.Ammunition, 100, 0.1f){},
			crystalEarth = new Item("Earth Crystal", "", Type.Ammunition, 100, 0.1f){},
			portalStone = new ItemPortalStone(),
			
			mstoneSmall = new Item("Small Manastone", "", Type.Craft, 100, 5f){},
			mstone = new Item("Manastone", "", Type.Craft, 100, 10f){},
			mstoneLarge = new Item("Large Manastone", "", Type.Craft, 100, 20f){},
			
			slime = new Item("Slime", "", Type.Craft, 10, 3f){},
			feather = new Item("Feather", "A large that came from a very large bird", Type.Craft, 10, 3f){},
			antShell = new Item("Ant Shell", "A shell from a giant ant", Type.Craft, 10, 3f){},
			hardScale = new Item("Hard Scale", "Thick Scales from a giant cold blooded animal", Type.Craft, 10,5f){},
			foxHide =  new Item("Fox Hide", "", Type.Craft, 10, 2f){},
			rabbitHide =  new Item("Rabbit Hide", "", Type.Craft, 10, 2f){},
			wolfHide =  new Item("Wolf Hide", "", Type.Craft, 10, 7f){},
			boarHide =  new Item("Boar Hide", "", Type.Craft, 10, 3f){},
			
			all[] = {potionSmall, arrow, bullet, crystalWater, crystalFire, crystalWind, crystalEarth, portalStone, mstoneSmall, mstone, mstoneLarge, grenadeAmmo, slime, feather,antShell, hardScale, foxHide, rabbitHide, wolfHide, boarHide};
	public static ItemCustom customs[] = {new CopperDagger(), new GreatCopperSword(), new LongSword(), new LeatherGloves(), new LeatherHat(), new LeatherJacket(), new LeatherPants(), new LeatherShoes(), new MetalBoots(), new MetalHelm(),
		new MetalGuantlet(), new PlateArmorLower(), new PlateArmorUpper(),  new ClothRobe(), new BowShort(), new RifleBasic(), new SniperRifle(), new BasicMachinegun(), new CylinderBasic(), new ItemPortalStoneSaved(), new RifleAssault(),
		new WandPine()};
	
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
