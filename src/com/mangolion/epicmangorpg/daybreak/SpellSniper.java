package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillJumpBack;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashPrecise;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SpellSniper extends Character {

	public SpellSniper() {
		super("Spell Sniper", "A spell sniper that works for the Mantis squad."
				, 100, 200, 200,40, 20, 150, 50, 20, 0, 0,
				new Barehands(), new SkillBlock(),
				new SkillDisintegrationBeam(), new SkillStormTopaz());
		ai = new AISimple(this);
		isAllied = true;
	}

}
