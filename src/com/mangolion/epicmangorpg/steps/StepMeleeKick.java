package com.mangolion.epicmangorpg.steps;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgKickExecute;
import com.mangolion.epicmangorpg.messages.MsgKickLoad;
import com.mangolion.epicmangorpg.messages.MsgSkillInterrupt;
import com.mangolion.epicmangorpg.messages.MsgSkillInterruptFail;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillDodge.StepDodge;
import com.mangolion.epicmangorpg.skills.SkillParry.StepParry;
import com.mangolion.epicmangorpg.weapons.Weapons;

public abstract class StepMeleeKick extends StepMeleeSlash {


	public StepMeleeKick(Skill parent, String name, String desc, float timeLoad,
			float timeExecute, float timeCooldown, float hpCost, float mpCost,
			float stamCost, float balCost, float baseDamage_, float dmgPercent) {
		super(parent, name, desc, timeLoad, timeExecute,
				timeCooldown, hpCost, mpCost, stamCost, balCost, dmgPercent);
		setMessages(new MsgKickLoad(), new MsgKickExecute(), new MsgBasicCD());
		chanceBlock = 0.5f;
		chanceParry = 0;
		chanceMiss = 0.2f;
	}
	
	@Override
	public void init() {

		super.init();
	}
	
	@Override
	public void damage(Character target) {
		getCharacter().bal -= getDamage()/2;
		target.bal -= getDamage()*2;
		super.damage(target);
	}

	
}
