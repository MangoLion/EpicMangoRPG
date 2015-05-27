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

public class Reynald extends Character {

	public Reynald() {
		super("Reynald", "Small and skinny,Underneath his red hair were a pair of spring-green eyes, a narrow, almost-feminine nose, and lightly freckled cheeks sporting a gleeful grin. His features combined for an innocent, boyish look better suited for a high-school initiate than a collegiate academy student. However, he wore an outfit colored in the same burning-red as Ariadne's"
				, 100, 200, 200,50, 20, 150, 30, 20, 0, 0,
				new Kukri(), new SkillBlock(),
				new SkillKickBasic(), new SkillParry(),new SkillSlashBasic(), new SkillSideStep(), new SkillSidestepSlash(), new SkillStab(), new SkillSlashPrecise(), new SkillSideJump(),new SkillJumpBack(), new SkillRespite(),
				new SkillCatalystDispellingField(), new SkillArmorAuraBurst(), new SkillScorchEther());
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
