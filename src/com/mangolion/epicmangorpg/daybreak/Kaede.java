package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillArrowFlame;
import com.mangolion.epicmangorpg.skills.SkillArrowJumpBack;
import com.mangolion.epicmangorpg.skills.SkillArrowShoot;
import com.mangolion.epicmangorpg.skills.SkillArrowSideStep;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBoltIce;
import com.mangolion.epicmangorpg.skills.SkillBowSweep;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillJumpBack;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillMeditate;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillRespite;
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSideStep;
import com.mangolion.epicmangorpg.skills.SkillSidestepShoot;
import com.mangolion.epicmangorpg.skills.SkillSidestepSlash;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashPrecise;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.skills.SkillWait;
import com.mangolion.epicmangorpg.weapons.BowShort;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class Kaede extends Character {

	public Kaede() {
		super("Kaede", ""
				, 90, 200, 140,30, 25, 70, 50, 50, 0, 0,
				new BowShort(), new SkillBlock(),
				new SkillKickBasic(), new SkillParry(),new SkillBowSweep(), new SkillSideStep(), new SkillArrowSideStep(), new SkillSideJump(),new SkillArrowJumpBack(), new SkillRespite(), new SkillMeditate(),
				new SkillSelfBuffs(), new SkillArrowFlame(), new SkillArrowShoot(), new SkillHealSamaran(), new SkillKyun()
				);
		pronoun = "she";
		pronoun2 = "her";
		pronoun3 = "her";
		pronoun4 = "her";
		hpRegen = 0.02f;
		ai = new AISimple(this);
		isAllied = true;
		addBarrier(new Barrier(this, "Enchanted Garments", 50, 6, 6, 4, 100, 0.6f, Barrier.ALL));
		inventory.addItem(Items.arrow, 200);
	}

	public static class TwinBlade extends Weapon{

		public TwinBlade(){
			super("Twin-bladed Manteuffel sword", 12, 10, 0.9f, 1,
					0.9f, Weapons.Bladed, Weapons.Sword);
			// TODO Auto-generated constructor stub
		}
		
	}
}
