package com.mangolion.epicmangorpg.ais;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.skills.Skill;

public class AISimple extends AI {
	
	public float chanceDefend = 0.5f, chanceRecover = 0.5f;

	public AISimple(Character character) {
		super(character);
		// TODO Auto-generated constructor stub
	}
	
	public AISimple(Character character, float chanceDefend, float chanceRecover) {
		super(character);
		this.chanceDefend = chanceDefend;
		this.chanceRecover = chanceRecover;
	}
	
	public float recoverLevel = 0.5f;
	
	@Override
	public void nextAction() {
		if (character.target == null){
			Utility.narrate(character.name + " decided to stay idle this turn");
			character.ai = null;
			return;
		}
		
		if (!checkRecovery())
		if (!checkDefense())
		if (!executeSkill(GeneralType.Attack))
		if (!executeSkill(GeneralType.Defend)){
		
			Utility.narrate(character.name + " decided to stay idle this turn");
			super.nextAction();
		}
	}
	
	public boolean checkRecovery(){
		if (rand.nextFloat() > chanceRecover)
			return false;
		
		if (character.getHp()/character.getMaxHP() < recoverLevel){
			return executeSkill(ActionType.RecoverHP);
		}
		if (character.getSp()/character.getMaxSP() < recoverLevel){
			return executeSkill(ActionType.RecoverSP);
		}
		if (character.getMp()/character.getMaxMP() < recoverLevel){
			return executeSkill(ActionType.RecoverMP);
		}
		return false;
	}
	
	public boolean checkDefense(){
		Character charTarget = character.getTarget();
		if (charTarget == null)
			return false;
		
		Skill skill = charTarget.skillCurrent;
		if (skill == null || charTarget.getTarget() != character){
			//System.out.println("not targeted" +  character.getTarget() .name);
			return false;
		}
		
		if (skill.type.getGeneralType() == GeneralType.Attack && skill.isLoading && rand.nextFloat() <= chanceDefend){
			 return executeSkill(GeneralType.Defend);
		}
		return false;
	}
	
	public boolean executeSkill(ActionType type){
		int retry = 5;
		for (int i = 0; i < retry; i ++){
			Skill  skill = getRandomSkill(type);
			if (skill != null && skill.execute())
				return true;
		}
		return false;
	}
	
	public boolean executeSkill(GeneralType type){
		int retry = 5;
		for (int i = 0; i < retry; i ++){
			Skill  skill = getRandomSkill(type);
			if (skill != null && skill.execute())
				return true;
		}
		return false;
	}
}
