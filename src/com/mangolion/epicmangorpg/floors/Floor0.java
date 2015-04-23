package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.BlueSlime;
import com.mangolion.epicmangorpg.characters.Dummy;
import com.mangolion.epicmangorpg.characters.FangFox;
import com.mangolion.epicmangorpg.characters.FangRabbit;
import com.mangolion.epicmangorpg.characters.FangSpider;
import com.mangolion.epicmangorpg.characters.GiantRabbit;
import com.mangolion.epicmangorpg.characters.HealSlime;
import com.mangolion.epicmangorpg.characters.IceSprite;
import com.mangolion.epicmangorpg.characters.KingSlime;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.PoisonSlime;
import com.mangolion.epicmangorpg.characters.SoldierAnt;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.WildRabbit;
import com.mangolion.epicmangorpg.characters.WolfSpider;

public class Floor0 extends Floor {

	public Floor0() {
		addSpawn( BlueSlime.class, 0.2f);
		addSpawn(HealSlime.class, 0.2f);
		addSpawn( PoisonSlime.class, 0.15f);
		addSpawn( WildRabbit.class, 0.2f);
		addSpawn( GiantRabbit.class, 0.1f);
		addSpawn( WildFox.class , 0.2f);
	/*	addSpawn(IceSprite.class, 0.2f);
		addSpawn(IceSprite.class, 0.2f);*/
	}
}
