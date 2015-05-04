package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.AdventurerNewb;
import com.mangolion.epicmangorpg.characters.AntLionGiant;
import com.mangolion.epicmangorpg.characters.BirdGiant;
import com.mangolion.epicmangorpg.characters.ScropionWolf;
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
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.RabbitWild;
import com.mangolion.epicmangorpg.characters.SpiderWolf;
import com.mangolion.epicmangorpg.game.Terrain;

public class Floor4 extends Floor {

	public Floor4() {
		terrains.add(Terrain.Desert);
		terrains.add(Terrain.Desert);
		
		addSpawn(LizardKomodus.class, 0.2f);
		addSpawn(ScropionWolf.class, 0.2f);
		addSpawn(AntLionGiant.class, 0.2f);
		addSpawn(SpriteLightning.class, 0.2f);
		addSpawn(AntSoldier.class, 0.2f, 1.2f);
		addSpawn(BirdGiant.class, 0.2f);
		
		addAlly(AdventurerNewb.class, 1.4f);
		addAlly(AdventurerNewb.class, 1.4f);
	}
}
