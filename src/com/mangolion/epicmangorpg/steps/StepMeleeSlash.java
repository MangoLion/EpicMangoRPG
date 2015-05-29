package com.mangolion.epicmangorpg.steps;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgSkillInterrupt;
import com.mangolion.epicmangorpg.messages.MsgSkillInterruptFail;
import com.mangolion.epicmangorpg.messages.MsgSlashExecute;
import com.mangolion.epicmangorpg.messages.MsgSlashLoad;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillDodge.StepDodge;
import com.mangolion.epicmangorpg.skills.SkillParry.StepParry;
import com.mangolion.epicmangorpg.weapons.Weapons;

public abstract class StepMeleeSlash extends Step {



	public StepMeleeSlash(Skill parent, String name,  String desc, float timeLoad,
			float timeExecute, float timeCooldown, float dmgPercent) {
		super(parent, name, desc, ActionType.MeleeSwing, timeLoad, timeExecute,
				timeCooldown, dmgPercent);
		chanceBlock = 0.5f; // cancel both melee skills
		chanceParry = 0.5f;// both skills goes pass
							// each other and deal
							// damage,
		chanceMiss = 0f;
		doDamage = true;
		setMessages(new MsgSlashLoad(), null, new MsgBasicCD(), new MsgSlashExecute());
	}
	
	@Override
	public boolean checkCompatability(Character target) {
		if (target.isAirborne()){
			System.out.println("NOPEEEEE");
			return false;
		}
		return super.checkCompatability(target);
	}
	
	/*@Override
	public void execute(Character target, float time) {
		if (isCharged&& getCharacter().skillCharged != parent){
				getCharacter().skillCharged = parent;
				return;
		}
		
		
		if (!isAOE && !calculateChanceMelee(target)){
			if (damage(target))
				super.execute(target, time);
		}else  if (isAOE)
		{
			if (damage(target))
				super.execute(target, time);
		}
	}*/

	
}
