package com.mangolion.epicmangorpg.daybreak;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Barrier;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillCatalystDispellingField extends Skill{

	public SkillCatalystDispellingField() {
		super("Catalyst Dispelling Field", "Produces a burning-red aura (barrier)  that disintigrades incoming magic attacks, effective against thin but spread out and numerous attacks, not effective against strong/piercing magic atks. Last 5 seconds", ActionType.DefendMagic, Weapons.ALL);
		addSteps(new Step(this, "Catalyst Dispelling Field", "", ActionType.Barrier, 0.2f, 0.1f, 0, 0) {
			@Override
			public void execute(Character target, float time, String aug)  { 
				getCharacter().addBarrier(new Barrier(getCharacter(), "Catalyst Dispelling Field", 20, 0, 30, 5, 5, 1, Barrier.MAGIC));
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
		}.setCost(0, 20, 0, 0).
		setMessages(new Msg("$name held up $p3 right hand, fingers outstretched. "), 
				new Msg("Waves of antimagic poured off $name's shining glove as $p marched into the storm. $p3 burning-red aura surrounded him like a globe of protection, and every magic entity that touched its edge vanished from existence."),null));
	}

}
