package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.SlimeBlue;
import com.mangolion.epicmangorpg.characters.FoxFang;
import com.mangolion.epicmangorpg.characters.RabbitFang;
import com.mangolion.epicmangorpg.characters.SpiderFang;
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
import com.mangolion.epicmangorpg.characters.TankGuard;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.RabbitWild;
import com.mangolion.epicmangorpg.characters.SpiderWolf;
import com.mangolion.epicmangorpg.game.Terrain;

public class Floor5 extends Floor {

	public Floor5() {
		terrains.add(Terrain.Tundra);
		terrains.add(Terrain.HighMountain);
		terrains.add(Terrain.Arctic);
		
		addSpawn(SpriteIce.class, 0.2f, 1.2f);
		addSpawn( AntSoldier.class, 0.1f, 1.4f);
		addSpawn(SlimeHeal.class, 0.2f, 1.5f);
		addSpawn(TankGuard.class, 1);
	}
}
