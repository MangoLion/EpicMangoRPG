package com.mangolion.epicmangorpg.characters;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.floors.Floor;
import com.mangolion.epicmangorpg.floors.Floor0;

public class Characters {
	public static LinkedList<Character> getAllCharacters(){
		LinkedList<Character> result = new LinkedList<Character>();
		result.add(new CharacterPlayer("Player"));
		for (Floor floor: Floor.getFloors()){
			if (floor instanceof Floor0)
				continue;
			for (int i = 0; i < floor.spawns.size(); i ++){
				Character character = floor.getSpawn(i);
				result.add(character);
			}
			/*for (int i = 0; i < floor.allies.size(); i ++){
				Character character = floor.getAlly(i);
				result.add(character);
			}*/
		}
		return result;
	}
	
	public static Character getCharacter(String name){
		for (Character character: getAllCharacters()){
			if (character.name.equals(name))
				return character;
		}
		return null;
	}
}
