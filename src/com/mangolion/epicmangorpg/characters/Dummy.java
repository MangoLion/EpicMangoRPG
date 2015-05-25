package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AIFlight;
import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillFlight;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillArrowShoot;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.LongSword;

public class Dummy extends Character{

	public Dummy() {
		super("Dummy", "An indestructable dummy used for testing",10000000, 100, 100, 40, 10, 100, 10, 10,0,0, new Barehands());
		cpBase = -100000000;
	}

	@Override
	public float getCP() {
		// TODO Auto-generated method stub
		return CharacterPlayer.instance.getCP();
	}
	
	@Override
	public void nextAction() {
		summon = null;
		super.nextAction();
	}
}
