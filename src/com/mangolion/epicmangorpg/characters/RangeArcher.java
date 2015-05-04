package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillArrowFlame;
import com.mangolion.epicmangorpg.skills.SkillArrowJumpBack;
import com.mangolion.epicmangorpg.skills.SkillArrowPierce;
import com.mangolion.epicmangorpg.skills.SkillArrowRain;
import com.mangolion.epicmangorpg.skills.SkillArrowRapid;
import com.mangolion.epicmangorpg.skills.SkillArrowSideStep;
import com.mangolion.epicmangorpg.skills.SkillArrowSwift;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.BowShort;
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
	}

}
