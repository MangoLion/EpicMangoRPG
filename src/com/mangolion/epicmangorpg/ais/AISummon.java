package com.mangolion.epicmangorpg.ais;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.skills.Skill;

public class AISummon extends AISimple{

	public AISummon(Character character) {
		super(character);
	}
	
	@Override
	public void nextAction() {
		if (!checkSummon())
			super.nextAction();
	}
	
	public boolean checkSummon(){
		if (rand.nextFloat() <1 - character.getHp()/character.getMaxHP()){
			Skill skill = getRandomSkill(ActionType.Summon);
			if (skill != null){
				skill.execute();
				return true;
			}
		}
		return false;
	}

}
