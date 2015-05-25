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

public abstract class StepStab extends StepMeleeSlash {


	public StepStab(Skill parent, String name,  String desc, float timeLoad,
			float timeExecute, float timeCooldown, float dmgPercent) {
		super(parent, name,desc,  timeLoad, timeExecute,
				timeCooldown,dmgPercent);
		setMessages(null, null, null, null);
		chanceBlock = 0f;
		chanceParry = 0.7f;
		chanceMiss = 0.1f;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();

	}

	
}
