package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillMillionSlash;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;

public class CharacterBob extends Character{

	public CharacterBob() {
		super("Bob", "",100, 80, 100, 40, 10, 100, 10, 10, 0, 0,
				new LongSword(), new SkillBasicSwordCombo(),
				new SkillBlock(), new SkillKickBasic(), new SkillDodge(),
				new SkillParry(), new SkillStab(), new SkillSlashBasic(),
				new SkillHealBasic(), new SkillWait(), new SkillSlashHeavy(), new SkillMillionSlash(), new SkillSidestepSlash());
		ai = new AISimple(this);
	}

}
