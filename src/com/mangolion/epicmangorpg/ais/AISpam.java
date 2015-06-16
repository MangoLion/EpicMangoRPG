package com.mangolion.epicmangorpg.ais;

import java.util.Collections;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.skills.Skill;

public class AISpam extends AI {

	public AISpam(Character character) {
		super(character);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void nextAction() {
		LinkedList<Skill> skills =  character.skills;
		Collections.shuffle(skills);
		for (Skill skill: skills)
			if (skill.checkCondition()){
				skill.execute();
				return;
			}
		
		super.nextAction();
	}

}
