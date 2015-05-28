package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.events.Event;
import com.mangolion.epicmangorpg.events.EventRange;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Status;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillFlourishingBrambles extends Skill {

	public SkillFlourishingBrambles() {
		super("Flourishing Brambles", "Transmute a small seed that turns into a thick mass of vines, ensaring the enemy and dealing poison damage, also stunning them in the porccess. Parzifal's signature skill. Cannot be block or parried, has high accuracy.",Weapons.ALL, ActionType.Magic);
		addSteps(new Step(this, "Flourishing Brambles", "",ActionType.Magic, 0.5f, 0.1f, 0.1f,1f){
			@Override
			public void init() {
			strBased = false;
			intBased = true;
			dmgBase = 5;
			mpCost = 50;
			chanceBlock = 0;
			chanceParry = 0;
			chanceMiss = -0.3f;
			setElement(new Element("Poison", 1));
			setStatus(new StatusEnsnare(null, 1f), 1);
			msgSuccess = new Msg("Caught off-guard by the attack from $p3 rear, $name's $weapon slashed and cut on impulse. But there was simply far too many. The veritable horde of vines soon squeezed around $p3 body without mercy, spiked tendrils moving to push between every gap -- even a mouth opened to emit a painful cry.");
			setMessages(new Msg("A single sprout came forth from $name's conjuration"), new Msg("The transmutation magic $p poured in immediately grew it into a mass of thorny vines. Spreading out across the room, the spiked tendrils leaped towards $targetname  like an unstoppable torrent."), null);
				super.init();
			}
			@Override
			public void execute(Character target) {
				Event.addEvent(new EventIceBolt(1f, getCharacter(), target, 20, this));
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
	public class EventIceBolt extends EventRange{

		public EventIceBolt(float time, Character source, Character target, float dmgbase, Step step) {
			super("Flourishing Brambles", "", time, source, target, dmgbase, step);
			setMessages(null, null, null, new Msg("$name had parried $targetname's $targetskill"));
		}

}
	public static class StatusEnsnare extends Status{

		float lastReport = 0, reportRate = 1, dps = 20;
		
		public StatusEnsnare(Character character_, float time_) {
			super(character_, "ensnared", time_);
			doStun = true;
		}
		
		
		@Override
		public void tick(float deltaTime) {
			character.setDamage(name, deltaTime*character.getMaxHP()*0.3f, true);
			super.tick(deltaTime);
		}
		

	}
}
