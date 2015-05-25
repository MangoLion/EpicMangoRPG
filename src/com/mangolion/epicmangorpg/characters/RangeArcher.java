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
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.frames.FrameShopItemCustoms;
import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.ItemCustom;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillArrowFlame;
import com.mangolion.epicmangorpg.skills.SkillArrowJumpBack;
import com.mangolion.epicmangorpg.skills.SkillArrowPierce;
import com.mangolion.epicmangorpg.skills.SkillArrowRain;
import com.mangolion.epicmangorpg.skills.SkillArrowRapid;
import com.mangolion.epicmangorpg.skills.SkillArrowSideStep;
import com.mangolion.epicmangorpg.skills.SkillArrowSwift;
import com.mangolion.epicmangorpg.weapons.RifleAssault;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.BasicMachinegun;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.CopperDagger;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.SniperRifle;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class RangeArcher extends Character{

	public RangeArcher() {
		super("Archery Range", "A place for newbie archers", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands(), new SkillArrowFlame(), new SkillArrowJumpBack(), new SkillArrowPierce(), new SkillArrowRain(), new SkillArrowRapid(),  new SkillArrowSideStep(), new SkillArrowSwift());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		// TODO Auto-generated method stub
		new FrameShopSkill("Archery Range",new BowShort(), skills, Items.arrow);
		LinkedList<ItemCustom> items = new LinkedList<ItemCustom>();
	}

}
