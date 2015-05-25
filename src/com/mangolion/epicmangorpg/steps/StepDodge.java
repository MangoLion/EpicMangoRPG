package com.mangolion.epicmangorpg.steps;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgDodgeExecute;
import com.mangolion.epicmangorpg.messages.MsgDodgeLoad;
import com.mangolion.epicmangorpg.skills.Skill;

public abstract class StepDodge extends Step{
	public float value = 0.5f;
	public StepDodge(String name, Skill parent,
			float timeLoad, float timeExecute, float timeCooldown) {
		super(parent, name,"", ActionType.Dodge, timeLoad, timeExecute, timeCooldown, 0);
		setMessages(new MsgDodgeLoad(), new MsgDodgeExecute(), new MsgBasicCD(), null);
		
	}

	
}