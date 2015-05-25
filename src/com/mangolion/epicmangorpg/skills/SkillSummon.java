package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSummon extends Skill {
	public Class<? extends Character> summon;
	float chanceSuccess;
	boolean summoned = false;

	public SkillSummon(final Class<? extends Character> summon, final float chanceSuccess) {
		super("Summon", "Summon an ally",Weapons.ALL, ActionType.Summon);
		setObservable(false, 0);
		hasTarget = false;
		this.summon = summon;
		this.chanceSuccess = chanceSuccess; 
		addSteps(new Step(this, "Summon", "",ActionType.Summon, 1f, 0.1f, 0.2f, 0 ){
			@Override
			public void execute(Character target) {
				if (rand.nextFloat() <= chanceSuccess && !summoned){
				if (character.isAllied)
					Game.getInstance().charsAllies.add(Utility.getInstance(summon));
				else
					Game.getInstance().charsEnemies.add(Utility.getInstance(summon));
				summoned = true;
				super.execute(character);
				}
				else
					StylePainter.append(new Msg("There are no reinforcement around to respond to $name's call.").getMessage(getCharacter(), null, 0));
					
			}			
		}.setMessages(new Msg("Seeing the situation turning for the worst, $name called for backup"), null, null, new Msg("$name had successfully called for backup."))
		.setCost(20, 20, 0, 0));
	}

}
