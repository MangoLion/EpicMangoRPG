package com.mangolion.epicmangorpg.skills;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.characters.GolemEarth;
import com.mangolion.epicmangorpg.characters.GolemIce;
import com.mangolion.epicmangorpg.characters.GolemMetal;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillSummonGolem extends Skill{

	public SkillSummonGolem() {
		super("Summon Golem", "Summons a golem to fight by your side. Available golems are Earth,Metal and Ice.The command is: use skill summongolem with [Golem type]." , Weapons.Cylinder, ActionType.Summon);
		addSteps(new Step(this, name, desc, type, 1, 0.2f, 0, 0) {
			@Override
			public boolean checkConndition() {
				if (getCharacter().getSummon() != null){
					if (getCharacter() == CharacterPlayer.instance)
					Utility.narrate("You cannot have more than one summon at a time.");
					return false;
				}
				return super.checkConndition();
			}
			
			@Override
			public void execute(Character target, float time, String aug) {
				Character golem = null;
				if (aug.toLowerCase().equals("earth"))
					golem = new GolemEarth();
				else if (aug.toLowerCase().equals("metal"))
					golem = new GolemMetal();
				else if (aug.toLowerCase().equals("ice"))
					golem = new GolemIce();
				else {
					Utility.narrate("Thats not a golem type!");
					return;
				}
				Game.getInstance().addAlly(getCharacter(), golem);
				golem.isAllied = getCharacter().isAllied;
				super.execute(target, time);
			}
		});
	}

}
