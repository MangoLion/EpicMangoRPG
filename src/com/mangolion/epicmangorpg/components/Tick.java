package com.mangolion.epicmangorpg.components;

import java.awt.BorderLayout;

import com.mangolion.epicmangorpg.characters.Character;

public class Tick {
	public static final int SKILL = 0,
								ACTION = 1;
	
	public int type;
	public Character character;
	public float time;
	
	public Tick(Character character_, float time_, int type) {
		character = character_;
		time = time_;
		this.type = type;
	}
}
