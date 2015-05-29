package com.mangolion.epicmangorpg.events;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.StyleSegment;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgParrySuccess;
import com.mangolion.epicmangorpg.messages.MsgSkillInterrupt;
import com.mangolion.epicmangorpg.messages.MsgSkillInterruptFail;
import com.mangolion.epicmangorpg.messages.MsgSlashMiss;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillParry.StepParry;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.steps.Step;

public class EventRange extends Event{
	public float chanceMiss = 0f, chanceDodge  = 1, chanceParry = 1, chanceBlock =1;
	public Msg msgLoad, msgCooldown, msgMiss = new MsgSlashMiss(), msgParry = new MsgParrySuccess();
	public float dmgBase, chanceStatus = 0;
	public Status status;
	public boolean isAOE = false;

	public EventRange(String name, String desc, float time, Character souce, float dmgbase, Step step) {
		super(name, desc, time, souce, null, null, step);
		this.dmgBase = dmgbase;
	}
	
	public EventRange(String name, String desc, float time, Character souce,
			Character target, float dmgbase, Step step) {
		super(name, desc, time, souce, target, null, step);
		this.dmgBase = dmgbase;
	}
	
	public EventRange(String name, String desc, float time, Character souce,
			Character target, float dmgbase, Step step, boolean aoe) {
		super(name, desc, time, souce, target, null, step);
		this.dmgBase = dmgbase;
		isAOE = aoe;
	}
	
	@Override
	public void execute() {
		if (target == null)
			return;
			if(step.damage(target))
				super.execute();
	}
	
	public float getDamage(){
		return dmgBase;
	}
	
	public Event setChances(float block, float parry, float miss, float dodge) {
		chanceBlock = block;
		chanceParry = parry;
		chanceMiss = miss;
		chanceDodge = dodge;
		return this;
	}
	
	public Event setMessages(Msg load, Msg execute, Msg cooldown, Msg parry) {
		msgLoad = load;
		msgExecute = execute;
		msgCooldown = cooldown;
		msgParry = parry;
		return this;
	}
}
