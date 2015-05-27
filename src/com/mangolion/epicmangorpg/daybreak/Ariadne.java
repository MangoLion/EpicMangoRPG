package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.Barrier;
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

public class Ariadne extends Character {

	public Ariadne() {
		super("Ariadne", "With waist-length cascades of bubblegum-pink hair held back by a flowery bun, the lady held a breathtaking beauty that radiated confidence and refinement. Her clear, bright-cyan eyes seemed to sparkle above the naturally sweet and gentle smile of her cherry lips. Her slender body was athletic yet wrapped by enticing curves, striding forth with firm and elegant steps. Her figure-hugging military uniform was black-bordered and burning red, with a gleaming-black short tie and collars, instead of the crimson-on-black outfit Pascal wore with his Knight's Cross. Accentuated with artistic strokes of orange and yellow, her outfit almost seemed like it was lit alight with flames."
				, 100, 200, 200,50, 20, 150, 30, 20, 0, 0,
				new TwinBlade(), new SkillBlock(),
				new SkillKickBasic(), new SkillParry(),new SkillSlashBasic(), new SkillSideStep(), new SkillSidestepSlash(), new SkillStab(), new SkillSlashPrecise(), new SkillSideJump(),new SkillJumpBack(), new SkillRespite(),
				new SkillEtherSeeker(), new SkillIgnitionDispel()
				);
		pronoun = "she";
		pronoun2 = "her";
		pronoun3 = "her";
		pronoun4 = "her";
		ai = new AISimple(this);
		isAllied = true;
		addBarrier(new Barrier(this, "Enchanted Armor", 50, 6, 6, 4, 20, 0.6f, Barrier.ALL));
	}

	public static class TwinBlade extends Weapon{

		public TwinBlade(){
			super("Twin-bladed Manteuffel sword", 12, 10, 0.9f, 1,
					0.9f, Weapons.Bladed, Weapons.Sword);
			// TODO Auto-generated constructor stub
		}
		
	}
}
