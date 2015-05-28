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

public class SkillEtherSeeker extends Skill {

	public SkillEtherSeeker() {
		super("Ether Seeker", "Fires projectiles that intercept incoming ranged attacks, only intercept projectiles heading towards the user, not other allies, does not work against AOE atks.",Weapons.ALL, ActionType.DefendMagic);
		addSteps(new Step(this, "Ether Seeker", "",ActionType.DefendMagic, 0.3f, 0.1f, 0.4f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = false;
			dmgBase = 0;
			mpCost = 15;
			setMessages(new Msg("Seeing the numerous ranged attacks aimed at $name, $p prepares to intercept them"), new Msg("The defensive homing bullets shot out from $name with a wave of $p3 $weapon. Streaking across the open air"), null);
				super.init();
			}
			@Override
			public void execute(Character target) {
				for (int i = 0; i < 4; i ++)
					Event.addEvent(new EventFireBolt(0.6f, getCharacter(), target, 20, this));
				super.execute(character);
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
			super("Ether Seeker", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}
		
		@Override
		public void execute() {
			
			//super.execute();
		}
		
		@Override
		public void tick(float dtime) {
			Iterator<Event> it = Game.getInstance().events.iterator();
			while(it.hasNext()){
				Event event = it.next();
				if (event.target == character && !event.step.isAOE){
					it.remove();
					StylePainter.append(new Msg("$name's seeker had intercepted $targetname's " +event. name).getMessage(character, event.source, 0));
					time = -1;
					break;
				}
			}
			super.tick(dtime);
		}
}
}
