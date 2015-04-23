package com.mangolion.epicmangorpg.ais;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
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
		if (!checkRecovery())
		if (!checkDefense()){
			Skill skill = getRandomSkill(GeneralType.Attack);
			if (skill != null){
				skill.execute();
			}else{
				 skill = getRandomSkill(GeneralType.Defend);
				 if (skill != null)
					 skill.execute();
			}
		}
			
		super.nextAction();
	}
	
	public boolean checkRecovery(){
		if (rand.nextFloat() > chanceRecover)
			return false;
		
		if (character.hp/character.maxHP < recoverLevel){
			Skill skill = getRandomSkill(ActionType.RecoverHP);
			if (skill != null){
				skill.execute();
				return true;
			}
		}
		if (character.sp/character.maxSP < recoverLevel){
			Skill skill = getRandomSkill(ActionType.RecoverSP);
			if (skill != null){
				skill.execute();
				return true;
			}
		}
		if (character.mp/character.maxMP < recoverLevel){
			Skill skill = getRandomSkill(ActionType.RecoverMP);
			if (skill != null){
				skill.execute();
				return true;
			}
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
			 skill = getRandomSkill(GeneralType.Defend);
			if (skill != null){
				skill.execute();
				return true;
			}
		}
		return false;
	}
}
