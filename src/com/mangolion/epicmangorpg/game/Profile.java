package com.mangolion.epicmangorpg.game;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;

public class Profile {
	public int currentFloor;
	public float floorPercent = 0;
	public boolean nextFloor, lastFloor;
	public Character player;
	public String theme;
	
	public Profile(Character player,  int currentFloor, float floorPercent, boolean nextFloor, boolean lastFloor, String theme) {
		this.player = player;
		this.currentFloor = currentFloor;
		this.floorPercent = floorPercent;
		this.nextFloor = nextFloor;
		this.lastFloor = lastFloor;
		this.theme = theme;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return player.name;
	}
}
