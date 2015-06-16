package com.mangolion.epicmangorpg.floors;

import java.util.LinkedList;
import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Terrain;
import com.mangolion.epicmangorpg.game.Utility;

public class Floor {
	public LinkedList<Spawn> spawns = new LinkedList<Floor.Spawn>();
	public LinkedList<Spawn> allies = new LinkedList<Floor.Spawn>();
	public LinkedList<Terrain> terrains = new LinkedList<Terrain>();
	Random rand = new Random();
	public void addSpawn( Class<? extends Character> character, float chance){
		spawns.add(new Spawn(character, chance, 1));
	}

	public void addSpawn( Class<? extends Character> character, float chance, float scale){
		spawns.add(new Spawn(character, chance, scale));
	}
	
	public void addAlly( Class<? extends Character> character, float scale){
		allies.add(new Spawn(character, scale));
	}

	
	public Character getSpawn(){
		/*for (Spawn spawn:spawns)
			if (rand.nextFloat() <= spawn.chance){
				Character character =  Utility.getInstance(spawn.character);
				if (spawn.scale != 1)
					character.scale(spawn.scale);
				return character;
		}*/
		Spawn spawn = spawns.get(rand.nextInt(spawns.size()));
		Character character = Utility.getInstance(spawn.character);
		character.scale(spawn.scale);
		return  character;
	}
	
	public Character getAlly(){
		return  Utility.getInstance( allies.get(rand.nextInt(allies.size())).character);
	}
	
	public Character getAlly(int i) {
		return  Utility.getInstance( allies.get(i).character);
	}
	
	public Character getSpawn(int i){
		return Utility.getInstance( spawns.get(i).character);
	}

	public static class Spawn{
		public Class<? extends Character> character;
		public float chance, scale = 1;
		
		public Spawn( Class<? extends Character>character_) {
			chance = 1;
			character = character_;
		}
		
		public Spawn( Class<? extends Character>character_, float scale_) {
			scale = scale_;
			character = character_;
			chance = 1;
		}
		
		public Spawn( Class<? extends Character>character_, float chance_, float scale_) {
			chance = chance_;
			character = character_;
			scale = scale_;
		}
		
	}

	public static LinkedList<Floor> getFloors() {
		LinkedList<Floor> result = new LinkedList<Floor>();
		result.add(new Floor0());
		//result.add(new FloorDayBreak());
		result.add(new Floor1());
		result.add(new Floor2());
		result.add(new Floor3());
		result.add(new Floor4());
		result.add(new Floor5());
		
		return result;
	}


}
