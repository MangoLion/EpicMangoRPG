package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillMeditate;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillSidestepThurst;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashPrecise;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillThrurst;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class Pascal extends Character {

	public Pascal() {
		super("Pascal", " The only heir of Field Marshal and Landgrave von Moltewitz of Nordkreuz, framed by soft golden curls just long enough to cover his ears, his appearance stayed lit by a flawless gentle smile. His broad yet lean build gave his polished military uniform the best look a propaganda poster could seek."
				, 100, 200, 200, 40, 20, 150, 40, 20, 0, 0,
				new Rapier(), new SkillBlock(),new SkillRespite(), new SkillMeditate(),
				new SkillKickBasic(), new SkillParry(),new SkillSlashBasic(), new SkillSideStep(), new SkillSidestepThurst(), new SkillThrurst(), new SkillSlashPrecise(), new SkillSideJump(), new SkillWait(),
				new SkillDefensiveRunes(), new SkillSelfBuffs(),new SkillCycloneBlast());
		ai = new AISimple(this);
		isAllied = true;
	}

	public static class Rapier extends Weapon{

		public Rapier(){
			super("Rapier", 15, 10, 0.9f, 0,
					1f, Weapons.Bladed, Weapons.Rapier);
			
		}
		
	}
}
