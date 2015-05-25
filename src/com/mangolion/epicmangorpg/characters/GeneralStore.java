package com.mangolion.epicmangorpg.characters;

import java.util.Arrays;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.frames.FrameShopItems;
import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBlastFlame;
import com.mangolion.epicmangorpg.skills.SkillBlastSand;
import com.mangolion.epicmangorpg.skills.SkillBlastWind;
import com.mangolion.epicmangorpg.skills.SkillSummonGolem;
import com.mangolion.epicmangorpg.skills.SkillWaterCannon;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;

public class GeneralStore extends Character{

	public GeneralStore() {
		super("General Store", "", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		LinkedList<Item> items = new LinkedList<Item>();
		items.add(Items.arrow);
		items.add(Items.potionSmall);
		items.add(Items.bullet);
		items.add(Items.crystalEarth);
		items.add(Items.crystalFire);
		items.add(Items.crystalWater);
		items.add(Items.crystalWind);
		items.clear();
		items.addAll(Arrays.asList(Items.all));
		new FrameShopItems("General Store", items);
	}

}
