package com.mangolion.epicmangorpg.floors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Utility;

public class Floor {
	public LinkedList<Spawn> spawns = new LinkedList<Floor.Spawn>();
	Random rand = new Random();
	public void addSpawn( Class<? extends Character> character, float chance){
		spawns.add(new Spawn(character, chance));
	}
	
	public Character getSpawn(){
		for (Spawn spawn:spawns)
			if (rand.nextFloat() <= spawn.chance)
				return Utility.getInstance(spawn.character);
		return Utility.getInstance( spawns.get(rand.nextInt(spawns.size())).character);
	}
	
	public Character getSpawn(int i){
		return Utility.getInstance( spawns.get(i).character);
	}

	public static class Spawn{
		public Class<? extends Character> character;
		public float chance;
		
		public Spawn( Class<? extends Character>character_, float chance_) {
			chance = chance_;
			character = character_;
		}
		
	}

	public static LinkedList<Floor> getFloors() {
		LinkedList<Floor> result = new LinkedList<Floor>();
		result.add(new Floor0());
		result.add(new Floor1());
		result.add(new Floor2());
		return result;
	}
}
