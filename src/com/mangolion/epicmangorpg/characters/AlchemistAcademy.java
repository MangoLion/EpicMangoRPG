package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBlastFlame;
import com.mangolion.epicmangorpg.skills.SkillBlastSand;
import com.mangolion.epicmangorpg.skills.SkillBlastWind;
import com.mangolion.epicmangorpg.skills.SkillSummonGolem;
import com.mangolion.epicmangorpg.skills.SkillWaterCannon;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;

public class AlchemistAcademy extends Character{

	public AlchemistAcademy() {
		super("Alchemist Academy", "A place for newbie battle alchemist", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands(), new SkillWaterCannon(), new SkillBlastFlame(), new SkillSummonGolem(), new SkillBlastWind(), new SkillBlastSand());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		// TODO Auto-generated method stub
		new FrameShopSkill("Alchemist Academy",new CylinderBasic(), skills, Items.bullet);
	}

}
