package com.mangolion.epicmangorpg.daybreak;



import java.util.LinkedList;
import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventArrow;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillRicochetingBlade extends Skill {

	public SkillRicochetingBlade() {
		super("Ricocheting Blade", "Hurls a specially enchanted kukri that multiply each time it bounces off the wall.",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "Ricocheting Blade", "",ActionType.Magic, 0.4f, 0.1f, 0.6f,0.9f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 35;
			stamCost =  35;
			chanceMiss = -0.2f;
			setEvents(new EventMainBlast(0.7f, getCharacter(), null, 20, this));
			setMessages(new Msg(" $p hurled a specially enchanted kukri outside. "),new Msg("The curved blade ricocheted off the walls with perfect bounces, multiplying into three each time. Within mere seconds, a roaming cloud of whirling steel swept down the hall, dicing through anything softer than rock with impunity."), null);
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
	
	@Override
	public float getTotalDamage() {
		// TODO Auto-generated method stub
		return 10000;
	}
	
	public class EventMainBlast extends EventRange{

		public EventMainBlast(float time, Character source, Character target, float dmgbase, Step step) {
			super("Ricocheting Blade", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
			if (Game.getInstance().timePassed > 0){
			LinkedList<Character> enemies = Game.getInstance().getEnemies(source);
			if (enemies.size() > 1)
				this.target = enemies.get(new Random().nextInt(enemies.size()));}
		}

		
		@Override
		public void tick(float dtime) {
			if (rand.nextInt(110) == 0)
				for (int i = 0; i < 3; i ++)
					Event.addEvent(new EventMainBlast(time, source, target, 0, step));
			super.tick(dtime);
		}

}
}
