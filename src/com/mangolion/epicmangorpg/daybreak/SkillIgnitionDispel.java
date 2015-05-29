package com.mangolion.epicmangorpg.daybreak;



import java.util.Iterator;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillIgnitionDispel extends Skill {

	public SkillIgnitionDispel() {
		super("Ignition Dispel", "Throw a dispel grenade towards the target, the grenade will either dispel the target's AOE event OR cancel the target's loading magic skill, if any.",Weapons.ALL, ActionType.DefendMagic);
		addSteps(new Step(this, "Ignition Dispel", "",ActionType.Magic, 0.3f, 0.1f, 0f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = false;
			dmgBase = 0;
			mpCost = 20;
			setEvents(new EventFireBolt(0.2f, getCharacter(), null, 20, this));
			setMessages(null, new Msg("$name reached $p3  right hand out, a wooden grenade zoomed from a belt pouch into $p3 palm before $p hurled it towards the keep, followed closely by a shouted 'Ignition Dispel!''"), null);
				super.init();
			}
	
			
			@Override
			public float getIntBuff() {
				// TODO Auto-generated method stub
				return prof*10;
			}
		});
		setObservable(true, 0.7f);
	}
	public class EventFireBolt extends EventRange{

		public EventFireBolt(float time, Character source, Character target, float dmgbase, Step step) {
			super("Dispel Grenade", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}
		
		@Override
		public void execute() {
			Iterator<Event> it = Game.getInstance().events.iterator();
			while(it.hasNext()){
				Event event = it.next();
				if (event.target == character && event.step.isAOE){
					it.remove();
					StylePainter.append(new Msg("$name's dispel grenade had intercepted $targetname's " + step.name).getMessage(character, event.source, 0));
					return;
				}
			}
			Skill skill = target.skillCurrent;
			Step step = skill.steps.get(skill.stepCurrent);
			if (target.skillCurrent != null && step.intBased){
				step.cancel();
				StylePainter.append(new Msg("$name's dispel grenade had intercepted $targetname's $targetskill.").getMessage(character, target, 0));
				return;
			}
			//super.execute();
		}
		
}
}
