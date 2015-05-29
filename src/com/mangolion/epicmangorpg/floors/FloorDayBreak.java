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
import com.mangolion.epicmangorpg.daybreak.Ariadne;
import com.mangolion.epicmangorpg.daybreak.Kaede;
import com.mangolion.epicmangorpg.daybreak.MantisAssassin;
import com.mangolion.epicmangorpg.daybreak.Parzifal;
import com.mangolion.epicmangorpg.daybreak.Pascal;
import com.mangolion.epicmangorpg.daybreak.Reynald;
import com.mangolion.epicmangorpg.daybreak.SpellSniper;
import com.mangolion.epicmangorpg.game.Terrain;
import com.mangolion.epicmangorpg.grave.Lute;
import com.mangolion.epicmangorpg.grave.Theodore;

public class FloorDayBreak extends Floor {

	public FloorDayBreak() {
		terrains.add(Terrain.Plain);
		terrains.add(Terrain.Taiga);

		addSpawn(Theodore.class, 0.2f);
		addSpawn(Lute.class, 0.2f);
		addSpawn(Kaede.class, 0.2f);
		addSpawn(Ariadne.class, 0.2f);
		addSpawn(Parzifal.class, 0.2f);
		addSpawn(Pascal.class, 0.2f);
		addSpawn(Reynald.class, 0.2f);
		addSpawn(MantisAssassin.class, 0.2f);
		addSpawn(SpellSniper.class, 0.2f);
		addSpawn(CharacterLine.class, 0.2f);
		addAlly(AdventurerNewb.class, 1);

	}
}
