package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.frames.FrameShopSkill;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillMillionSlash;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillSlashPrecise;
import com.mangolion.epicmangorpg.skills.SkillSlashSpin;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.LongSword;

public class WarriorTrainingHall extends Character{

	public WarriorTrainingHall() {
		super("Warrior Training Hall", "A place for newbie warriors", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, new Barehands(),new SkillSlashHeavy(), new SkillBasicSwordCombo(), new SkillMillionSlash(), new SkillSidestepSlash(),new SkillSlashSpin(), new SkillSlashPrecise());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		// TODO Auto-generated method stub
		new FrameShopSkill("Warrior Training Hall",new LongSword(), skills, Items.bullet);
	}

}
