package com.mangolion.epicmangorpg.daybreak;



import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillScorchEther extends Skill {

	public SkillScorchEther() {
		super("Scorch-Ether", "Combining Catalyst, Fragmentation and Dispel used for bypassing barriers. Fragments on impact with barrier, fire element.",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "Scorch-Ether", "",ActionType.Magic, 0.5f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 25;
			mpCost =  45;
			chanceBlock = 0;
			setElement(new Element("Fire", 1));
			setEvents(new EventMainBlast(0.5f, getCharacter(), null, 20, this));
			setMessages(null,new Msg("'Scorch-Ether, Catalyst Fragmentation Dispel!' $name yelled as $p releases an X-shaped fire blast, which procceded to suck in atmosphere like a black hole as it soared towards $targetname"), null);
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
	public class EventMainBlast extends EventRange{

		public EventMainBlast(float time, Character source, Character target, float dmgbase, Step step) {
			super("Main Blast", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}
		
		@Override
		public void execute() {
			super.execute();
			if (target.barriers.size() > 0){
			for (int i = 0; i < 4; i ++)
				Event.addEvent(new EventFragmentBlast(0.3f, character, target, 0, step));
			StylePainter.append(new Msg("$name's " + name + " has fragmented into four upon impact into $targetname's barrier").getMessage(character, target, 0));
			}
			
		}

}
	public class EventFragmentBlast extends EventRange{

		public EventFragmentBlast(float time, Character source, Character target, float dmgbase, Step step) {
			super("Fragmented Blast", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			//setStatus(new StatusPoison(null, 1), 1);
		}

}
}
