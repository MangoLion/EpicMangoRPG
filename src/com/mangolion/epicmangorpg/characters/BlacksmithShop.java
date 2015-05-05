package com.mangolion.epicmangorpg.characters;

import java.util.LinkedList;

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
import com.mangolion.epicmangorpg.frames.FrameShopItemCustoms;
import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBlastFlame;
import com.mangolion.epicmangorpg.skills.SkillBlastSand;
import com.mangolion.epicmangorpg.skills.SkillBlastWind;
import com.mangolion.epicmangorpg.skills.SkillSummonGolem;
import com.mangolion.epicmangorpg.skills.SkillWaterCannon;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.BasicMachinegun;
import com.mangolion.epicmangorpg.weapons.BasicRifle;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.CopperDagger;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.SniperRifle;

public class BlacksmithShop extends Character{

	public BlacksmithShop() {
		super("Blacksmith", "A place for buying weapons", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		LinkedList<ItemCustom> items = new LinkedList<ItemCustom>();
		items.add(new CylinderBasic());
		items.add(new BasicMachinegun());
		items.add(new SniperRifle());
		items.add(new BasicRifle());
		items.add(new BowShort());
		items.add(new CopperDagger());
		items.add(new LeatherGloves());
		items.add(new LeatherPants());
		items.add(new LeatherJacket());
		items.add(new LeatherHat());
		items.add(new LeatherShoes());
		items.add(new MetalBoots());
		items.add(new MetalGuantlet());
		items.add(new MetalHelm());
		items.add(new PlateArmorLower());
		items.add(new PlateArmorUpper());
		items.add(new ClothRobe());
		items.add(new GreatCopperSword());
		new FrameShopItemCustoms("Blacksmith", items);
	}

}
