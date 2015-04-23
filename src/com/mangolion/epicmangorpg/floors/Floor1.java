package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.BlueSlime;
import com.mangolion.epicmangorpg.characters.FangFox;
import com.mangolion.epicmangorpg.characters.FangRabbit;
import com.mangolion.epicmangorpg.characters.FangSpider;
import com.mangolion.epicmangorpg.characters.GiantRabbit;
import com.mangolion.epicmangorpg.characters.GiantRat;
import com.mangolion.epicmangorpg.characters.HealSlime;
import com.mangolion.epicmangorpg.characters.KingSlime;
import com.mangolion.epicmangorpg.characters.KomodusLizard;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.PoisonSlime;
import com.mangolion.epicmangorpg.characters.SoldierAnt;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.WildRabbit;
import com.mangolion.epicmangorpg.characters.WolfSpider;

public class Floor1 extends Floor {

	public Floor1() {
		addSpawn( GiantRat.class, 0.1f);
		addSpawn( FangRabbit.class, 0.15f);
		addSpawn(HealSlime.class, 0.2f);
		addSpawn( FangFox.class, 015f);	
		addSpawn(KomodusLizard.class, 0.2f);
		addSpawn( WolfSpider.class, 0.2f);
		addSpawn( FangSpider.class, 0.15f);
		addSpawn( KingSlime.class, 0.1f);
	}
}
