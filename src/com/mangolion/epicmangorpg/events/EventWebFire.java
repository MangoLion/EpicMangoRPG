package com.mangolion.epicmangorpg.events;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.messages.MsgSlashExecute;
import com.mangolion.epicmangorpg.messages.MsgSlashLoad;
import com.mangolion.epicmangorpg.steps.Step;

public class EventWebFire extends EventRange{

	public EventWebFire(float time, Character source, Character target, float dmgbase, Step step) {
		super("Web", "", time, source, target, dmgbase, step);
		setMessages(null, null, new MsgBasicCD(), new Msg("$name had parried $targetname's webs"));
	}

}
