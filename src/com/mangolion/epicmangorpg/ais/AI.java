package com.mangolion.epicmangorpg.ais;

import java.util.LinkedList;
import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.GeneralType;
import com.mangolion.epicmangorpg.skills.Skill;

public class AI {
	public Character character;
	Random rand = new Random();
	public AI(Character character) {
		this.character = character;
	}
	
	public Skill getRandomSkill(GeneralType type){
		LinkedList<Skill> skills = new LinkedList<Skill>();
		for (Skill skill: character.skills)
			if (skill.type.getGeneralType() == type)
				skills.add(skill);
		if (skills.size() > 1)
			return skills.get(rand.nextInt(skills.size()));
		if (skills.size() == 1)
			return skills.getFirst();
		return null;
	}
	
	public Skill getRandomSkill(ActionType type){
		LinkedList<Skill> skills = new LinkedList<Skill>();
		for (Skill skill: character.skills)
			if (skill.type== type)
				skills.add(skill);
		if (skills.size() > 1)
			return skills.get(rand.nextInt(skills.size()));
		if (skills.size() == 1)
			return skills.getFirst();
		return null;
	}
	
	public void nextAction(){
		
	}
}
