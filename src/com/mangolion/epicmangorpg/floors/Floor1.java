package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.AdventurerNewb;
import com.mangolion.epicmangorpg.characters.BirdGiant;
import com.mangolion.epicmangorpg.characters.RatGiant;
import com.mangolion.epicmangorpg.characters.SlimeBlue;
import com.mangolion.epicmangorpg.characters.Dummy;
import com.mangolion.epicmangorpg.characters.FoxFang;
import com.mangolion.epicmangorpg.characters.RabbitFang;
import com.mangolion.epicmangorpg.characters.SpiderFang;
import com.mangolion.epicmangorpg.characters.RabbitGiant;
import com.mangolion.epicmangorpg.characters.SlimeHeal;
import com.mangolion.epicmangorpg.characters.SpriteIce;
import com.mangolion.epicmangorpg.characters.SlimeKing;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.SlimePoison;
import com.mangolion.epicmangorpg.characters.TurtleRock;
import com.mangolion.epicmangorpg.characters.AntSoldier;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.RabbitWild;
import com.mangolion.epicmangorpg.characters.SpiderWolf;
import com.mangolion.epicmangorpg.game.Terrain;

public class Floor1 extends Floor {

	public Floor1() {
		terrains.add(Terrain.Plain);
		terrains.add(Terrain.Taiga);
		
		addSpawn( SlimeBlue.class, 0.2f);
		addSpawn(SlimeHeal.class, 0.2f);
		addSpawn( SlimePoison.class, 0.15f);
		addSpawn( RabbitWild.class, 0.2f);
		addSpawn( RabbitGiant.class, 0.1f);
		addSpawn( WildFox.class , 0.2f);
		addSpawn(BirdGiant.class, 0.2f);
		//addSpawn(RockTurtle.class, 1);
	//	addSpawn(Dummy.class, 1);
		
		addAlly(AdventurerNewb.class, 1);

	}
}
