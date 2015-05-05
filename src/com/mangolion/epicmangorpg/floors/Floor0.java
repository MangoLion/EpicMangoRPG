package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.AdventurerNewb;
import com.mangolion.epicmangorpg.characters.AlchemistAcademy;
import com.mangolion.epicmangorpg.characters.BlacksmithShop;
import com.mangolion.epicmangorpg.characters.GeneralStore;
import com.mangolion.epicmangorpg.characters.RangeArcher;
import com.mangolion.epicmangorpg.characters.BirdGiant;
import com.mangolion.epicmangorpg.characters.DungeonEntrance;
import com.mangolion.epicmangorpg.characters.RangeGun;
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
import com.mangolion.epicmangorpg.characters.WarriorTrainingHall;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.RabbitWild;
import com.mangolion.epicmangorpg.characters.SpiderWolf;
import com.mangolion.epicmangorpg.game.Terrain;

public class Floor0 extends Floor {

	public Floor0() {
		terrains.add(Terrain.Plain);
		terrains.add(Terrain.Taiga);
		
		addSpawn(DungeonEntrance.class, 0.2f);

		//addSpawn(RockTurtle.class, 1);
	//	addSpawn(Dummy.class, 1);
		
		addAlly(BlacksmithShop.class, 1);
		addAlly(GeneralStore.class, 1);
		addAlly(RangeArcher.class, 1);
		addAlly(RangeGun.class, 1);
		addAlly(AlchemistAcademy.class, 1);
		addAlly(WarriorTrainingHall.class, 1);

	}
}
