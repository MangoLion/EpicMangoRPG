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
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class MantisAssassin extends Character {

	public MantisAssassin() {
		super("Mantis Assassin", "One of the elites assassins sent to kill Pascal."
				, 100, 200, 200,50, 20, 150, 30, 20, 0, 0,
				new Kukri(), new SkillBlock(),
				new SkillKickBasic(), new SkillParry(),new SkillSlashBasic(), new SkillSideStep(), new SkillSidestepSlash(), new SkillStab(), new SkillSideJump(),new SkillJumpBack(), new SkillRespite(),
				new SkillNegationSurge(), new SkillRicochetingBlade());
		ai = new AISimple(this);
		isAllied = true;
	}

	public static class Kukri extends Weapon{

		public Kukri(){
			super("Kukri", 7, 10, 0.7f, 0,
					0.8f, Weapons.Bladed, Weapons.Dagger);
			// TODO Auto-generated constructor stub
		}
		
	}
}
