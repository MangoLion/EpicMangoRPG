package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.AdventurerNewb;
import com.mangolion.epicmangorpg.characters.BeeBumble;
import com.mangolion.epicmangorpg.characters.BirdGiant;
import com.mangolion.epicmangorpg.characters.BoarWild;
import com.mangolion.epicmangorpg.characters.SlimeBlue;
import com.mangolion.epicmangorpg.characters.FoxFang;
import com.mangolion.epicmangorpg.characters.RabbitFang;
import com.mangolion.epicmangorpg.characters.SpiderFang;
import com.mangolion.epicmangorpg.characters.SpriteEarth;
import com.mangolion.epicmangorpg.characters.SpriteFire;
import com.mangolion.epicmangorpg.characters.RabbitGiant;
import com.mangolion.epicmangorpg.characters.RatGiant;
import com.mangolion.epicmangorpg.characters.SlimeHeal;
import com.mangolion.epicmangorpg.characters.SpriteIce;
import com.mangolion.epicmangorpg.characters.SlimeKing;
import com.mangolion.epicmangorpg.characters.LizardKomodus;
import com.mangolion.epicmangorpg.characters.SpriteLightning;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.SlimePoison;
import com.mangolion.epicmangorpg.characters.AntSoldier;
import com.mangolion.epicmangorpg.characters.TurtleRock;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.RabbitWild;
import com.mangolion.epicmangorpg.characters.SpiderWolf;
import com.mangolion.epicmangorpg.characters.WolfGray;
import com.mangolion.epicmangorpg.characters.WolfWhite;
import com.mangolion.epicmangorpg.game.Terrain;

public class Floor3 extends Floor {

	public Floor3() {
		terrains.add(Terrain.Forest);
		terrains.add(Terrain.RoofedForest);
		terrains.add(Terrain.Jungle);
		terrains.add(Terrain.Swamp);
		
		addSpawn(LizardKomodus.class, 0.2f);
		addSpawn(TurtleRock.class, 0.2f);
		addSpawn(BeeBumble.class, 0.2f);
		addSpawn(SlimeHeal.class, 0.2f, 1.4f);
		addSpawn(LizardKomodus.class, 0.2f);
		addSpawn(SpriteEarth.class, 0.2f);
		addSpawn(WolfGray.class, 0.2f);
		addSpawn(WolfWhite.class, 0.2f);
		addSpawn(BoarWild.class, 0.2f);
		addSpawn(BirdGiant.class, 0.2f);

		
		addAlly(AdventurerNewb.class, 1.2f);
		addAlly(AdventurerNewb.class, 1.2f);
	}
}
