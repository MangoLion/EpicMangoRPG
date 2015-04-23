package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillBasicHeal;
import com.mangolion.epicmangorpg.skills.SkillBasicKick;
import com.mangolion.epicmangorpg.skills.SkillBasicSlash;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.WeaponGreatCopperSword;
import com.mangolion.epicmangorpg.weapons.WeaponLongSword;

public class Minotaur extends Character{

	public Minotaur() {
		super("Minotaur", "",300, 100, 100, 80, 10, 300, 10, 10, 0, 0,new WeaponGreatCopperSword(),  new SkillBasicSwordCombo(), new SkillBlock(),  new SkillBasicKick(), new SkillParry(), new SkillBasicSlash(), new SkillSlashHeavy());
		ai = new AISimple(this);
	}

}
