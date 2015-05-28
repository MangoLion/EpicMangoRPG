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
public class SkillDefensiveRunes extends Skill{

	public SkillDefensiveRunes() {
		super("Defensive Runes", "Activates 4 rune based barriers for protection, last 20 seconds, Pascal signature skill", ActionType.Barrier, Weapons.ALL);
		hasTarget = false;
		addSteps(new Step(this, "Defensive Runes", "", ActionType.Barrier, 0.7f, 0.1f, 0, 0) {
			public boolean checkConndition() {
				for (Barrier barrier: getCharacter().barriers)
					if (barrier.name.equals("Repulsion Field")){
						if (getCharacter() == CharacterPlayer.instance)
							Utility.narrate("You can only have one barrier of the same type");
						return false;
					}
				return super.checkConndition();
			};
			@Override
			public void execute(Character target, float time, String aug) {
				getCharacter().addBarrier(new Barrier(getCharacter(), "Repulsion Field", 40, 8, 0, 4, 20, 1));
				getCharacter().addBarrier(new Barrier(getCharacter(), "Spellshield Fortress", 50, 0, 0,0 , 20, 0.25f));
				getCharacter().addBarrier(new Barrier(getCharacter(), "Barrier Armor", 50, 10, 5, 5, 20, 1));
				getCharacter().addBarrier(new Barrier(getCharacter(), "Barrier Guard", 40, 0, 10, 4, 20, 1, Barrier.MAGIC));
				super.execute(target, time, aug);
			}
		}.setCost(20, 60, 0, 0)
		.setMessages(new Msg("$name's left hand stretched out with fingers extended, triggering a contingency effect as four rune-engraved pebbles materialized into $p3 hand. "),
				new Msg("$name is now surrounded with the invisible aura of $p3 anti-projectile Repulsion Field, five rotating turquoise shields of $p3 auto-blocking Spellshield Fortress, and the unseen plates of $p3 weightless Barrier Armor, which grew translucent as the magic-resistant Barrier Guard layered onto it."), null));
	}

}
