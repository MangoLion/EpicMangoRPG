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
		shopPrice = 70;
		setObservable(true, 1);
		setArguments("Stone", "Earth", "Ice", "Metal");
		hasTarget = false;
		addSteps(new Step(this, name, desc, type, 0.1f, 0.2f, 0, 0) {
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
				System.out.println("executed");
				if (!getCharacter().isPlayer)
					aug = parent.arguments.get(rand.nextInt(parent.arguments.size()));
				Character golem = null;
				if (aug.toLowerCase().equals("earth"))
					golem = new GolemEarth();
				else if (aug.toLowerCase().equals("metal"))
					golem = new GolemMetal();
				else if (aug.toLowerCase().equals("ice"))
					golem = new GolemIce();
				else {

					return;
				}
				getCharacter().summon = golem;
				golem.isAllied = getCharacter().isAllied;
				Game.getInstance().addCharacter(golem);
			}
			
			@Override
			public boolean checkConndition(String arg) {
				if (getCharacter().isPlayer)
					if (aug.toLowerCase().equals("earth")||aug.toLowerCase().equals("metal")||aug.toLowerCase().equals("ice"))
						return super.checkConndition(arg);
					else {}
				else
					return true;
				
				Utility.narrate("[" +aug + "] is not a golem type!");
				return false;
			}
		});
		
	}

}
