package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Buff.GenType;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Utility;
public class SkillArmorAuraBurst extends Skill{

	public SkillArmorAuraBurst() {
		super("Armor Aura Burst", "Conjure an invisible suit of magic armor for 20 seconds, gives 200% boost to agi for 2 seconds, armor's element is lightning", ActionType.Barrier, Weapons.ALL);
		hasTarget = false;
		addSteps(new Step(this, "Armor Aura Burst", "Anti-projectile barrier", ActionType.Barrier, 0.2f, 0.1f, 0, 0) {
			@Override
			public void execute(Character target, float time, String aug)  {
				getCharacter().addBarrier(new Barrier(getCharacter(), "Armor Aura Burst", 70, 10, 5, 5, 20, 1f, new Element("Lightning", 1)));
				getCharacter().applyBuff(new Buff("Armor Aura Boost", 2, GenType.positive, Buff.Type.agi).setValue( (prof + 1)*2f));
				super.execute(target,time,aug);
			}
			
			public boolean checkConndition() {
				for (Barrier barrier: getCharacter().barriers)
					if (barrier.name.equals(name)){
						if (getCharacter() == CharacterPlayer.instance)
							Utility.narrate("You can only have one barrier of the same type");
						return false;
					}
				return super.checkConndition();
			};
		}.setCost(20, 30, 0, 0).setMessages(new Msg("'Armor Aura Burst!' $name called"), new Msg("$name sends out a pressurized blast of air as $p used $p3 aura stance switch to conjure an invisible suit of magic armor."), null));
	}

}