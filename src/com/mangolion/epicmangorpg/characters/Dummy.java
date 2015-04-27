package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillShootArrow;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;

public class Dummy extends Character{

	public Dummy() {
		super("Dummy", "An indestructable dummy used for testing",1000000, 100, 100, 40, 10, 100, 10, 10,1000,0, new WeaponLongSword());
		ai = new AISimple(this);
	}

}
