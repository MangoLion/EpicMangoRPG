package com.mangolion.epicmangorpg.events;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgSlashExecute;
import com.mangolion.epicmangorpg.messages.MsgSlashLoad;
import com.mangolion.epicmangorpg.steps.Step;

public class EventArrow extends EventRange{

	public EventArrow(float time, Character source, Character target, float dmgbase, Step step) {
		super("Arrow", "", time, source, target, dmgbase, step);
		setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
	}
	
	public EventArrow(float time, Character source, Character target, float dmgbase, Step step, boolean isAOE) {
		super("Arrow", "", time, source, target, dmgbase, step, isAOE);
		setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
	}

}
