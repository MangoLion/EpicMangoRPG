package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.messages.MsgBasicCD;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.BuffEleFire;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillEarthCoating extends Skill{

	public SkillEarthCoating() {
		super("Elemental Body of Earth", "Coat your body  in Earth and gain some strength, you will receive more damages from opposing elements. Last for 10 seconds",Weapons.ALL, ActionType.Buff);
		addSteps(new StepEle(this, 0.1f, 0.2f, 0f).setCost(0,10, 0, 0));
		hasTarget = false;
	}

	public static class StepEle extends Step{
		public float chanceParry = 0.8f;
		public StepEle(Skill parent,
				float timeLoad, float timeExecute, float timeCooldown) {
			super(parent, "Elemental Body of Earth", "",ActionType.Buff, timeLoad, timeExecute, timeCooldown, 0);
			setMessages(new Msg("$name gathers elemental energy and prepares to project them onto $p3 body"), null, new MsgBasicCD());
			chanceParry= 1;
		}
		public float getIntBuff() {
			// TODO Auto-generated method stub
			return prof * 10;
		}
		public boolean checkConndition() {
			for (Buff buff: getCharacter().buffs)
				if (buff.name.equals(name)){
					if (getCharacter() == CharacterPlayer.instance)
						Utility.narrate("You can only have one buff of the same type");
					return false;
				}
			return super.checkConndition();
		};
		@Override
		public void execute(Character target, float time) {
			addProf(new Proficiency(getCharacter(), getCharacter()));
			getCharacter().applyBuff(new Buff("Elemental Body of Earth", 10, Buff.GenType.positive, Buff.Type.str).setValue(0.3f).setElement(new Element("Earth", 1)));
			super.execute(target, time);
		}

	}
}
