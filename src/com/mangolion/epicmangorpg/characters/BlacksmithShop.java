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
import com.mangolion.epicmangorpg.weapons.RifleAssault;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.BasicMachinegun;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
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
		FrameShopItemCustoms frame = new FrameShopItemCustoms("Blacksmith");
		frame.addTab("Swords", new CopperDagger(), new GreatCopperSword(), new LongSword());
		frame.addTab("Guns", new BasicMachinegun(), new BasicMachinegun(), new SniperRifle(), new RifleAssault());
		frame.addTab("Cylinders", new CylinderBasic());
		frame.addTab("Bows", new BowShort());
		frame.addTab("Armors", new LeatherGloves(), new LeatherHat(), new LeatherPants(), new LeatherJacket(),new LeatherShoes(), new MetalBoots(), new MetalGuantlet(), new MetalHelm(), new PlateArmorLower(), new PlateArmorUpper());
	}

}
