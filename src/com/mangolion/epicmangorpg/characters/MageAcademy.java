package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBlastFlame;
import com.mangolion.epicmangorpg.skills.SkillBlastSand;
import com.mangolion.epicmangorpg.skills.SkillBlastWind;
import com.mangolion.epicmangorpg.skills.SkillBoltFire;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillBoltLightning;
import com.mangolion.epicmangorpg.skills.SkillBoltRock;
import com.mangolion.epicmangorpg.skills.SkillBoltThorn;
import com.mangolion.epicmangorpg.skills.SkillBoltWater;
import com.mangolion.epicmangorpg.skills.SkillBoltWind;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillMeditate;
import com.mangolion.epicmangorpg.skills.SkillStormFire;
import com.mangolion.epicmangorpg.skills.SkillStormIce;
import com.mangolion.epicmangorpg.skills.SkillStormSand;
import com.mangolion.epicmangorpg.skills.SkillStormWind;
import com.mangolion.epicmangorpg.skills.SkillSummonGolem;
import com.mangolion.epicmangorpg.skills.SkillWaterCannon;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.RifleBasic;
import com.mangolion.epicmangorpg.weapons.CylinderBasic;

public class MageAcademy extends Character{

	public MageAcademy() {
		super("Mage  Academy", "A place for newbie mages", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands(), new SkillBoltFire(), new SkillBoltIce(), new SkillBoltLightning(), new SkillBoltRock(), new SkillHealBasic(), new SkillBoltWater(),
				 new SkillBoltWind(),new SkillBoltThorn(), new SkillStormFire(), new SkillStormIce(), new SkillStormWind(), new SkillStormSand(), new SkillMeditate());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		// TODO Auto-generated method stub
		new FrameShopSkill("Alchemist Academy",new CylinderBasic(), skills, Items.bullet);
	}

}
