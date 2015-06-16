package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISpam;
import com.mangolion.epicmangorpg.weapons.Barehands;

public class Instructor extends Character{

	public Instructor() {
		super("Instructor", "",270, 240, 100, 40, 10, 100, 10, 10, 0, 0,
				new Barehands());
		ai = new AISpam(this);
		isAllied = true;
	}

}
