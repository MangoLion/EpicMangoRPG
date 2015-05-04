package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.AdventurerNewb;
import com.mangolion.epicmangorpg.characters.BirdGiant;
import com.mangolion.epicmangorpg.characters.SlimeBlue;
import com.mangolion.epicmangorpg.characters.FoxFang;
import com.mangolion.epicmangorpg.characters.RabbitFang;
import com.mangolion.epicmangorpg.characters.SpiderFang;
import com.mangolion.epicmangorpg.characters.RabbitGiant;
import com.mangolion.epicmangorpg.characters.RatGiant;
import com.mangolion.epicmangorpg.characters.SlimeHeal;
import com.mangolion.epicmangorpg.characters.SlimeKing;
import com.mangolion.epicmangorpg.characters.LizardKomodus;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.SlimePoison;
import com.mangolion.epicmangorpg.characters.AntSoldier;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.RabbitWild;
import com.mangolion.epicmangorpg.characters.SpiderWolf;
import com.mangolion.epicmangorpg.game.Terrain;

public class Floor2 extends Floor {

	public Floor2() {
		terrains.add(Terrain.Forest);
		terrains.add(Terrain.Taiga);
		terrains.add(Terrain.Plain);
		
		addSpawn( RatGiant.class, 0.1f);
		addSpawn( RabbitFang.class, 0.15f);
		addSpawn(SlimeHeal.class, 0.2f, 1.2f);
		addSpawn( FoxFang.class, 015f);	
		addSpawn( SpiderWolf.class, 0.2f);
		addSpawn( SpiderFang.class, 0.15f);
		addSpawn( SlimeKing.class, 0.1f);
		addSpawn(BirdGiant.class, 0.2f);
		addSpawn(BirdGiant.class, 0.2f);
		
		addAlly(AdventurerNewb.class, 1);
		addAlly(AdventurerNewb.class, 1);
	}
}
