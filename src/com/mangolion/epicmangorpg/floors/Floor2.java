package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.BlueSlime;
import com.mangolion.epicmangorpg.characters.FangFox;
import com.mangolion.epicmangorpg.characters.FangRabbit;
import com.mangolion.epicmangorpg.characters.FangSpider;
import com.mangolion.epicmangorpg.characters.FireSprite;
import com.mangolion.epicmangorpg.characters.GiantRabbit;
import com.mangolion.epicmangorpg.characters.GiantRat;
import com.mangolion.epicmangorpg.characters.HealSlime;
import com.mangolion.epicmangorpg.characters.IceSprite;
import com.mangolion.epicmangorpg.characters.KingSlime;
import com.mangolion.epicmangorpg.characters.KomodusLizard;
import com.mangolion.epicmangorpg.characters.LightningSprite;
import com.mangolion.epicmangorpg.characters.Minotaur;
import com.mangolion.epicmangorpg.characters.PoisonSlime;
import com.mangolion.epicmangorpg.characters.SoldierAnt;
import com.mangolion.epicmangorpg.characters.WildFox;
import com.mangolion.epicmangorpg.characters.WildRabbit;
import com.mangolion.epicmangorpg.characters.WolfSpider;

public class Floor2 extends Floor {

	public Floor2() {
		addSpawn( SoldierAnt.class, 0.1f);
		addSpawn(HealSlime.class, 0.2f);
		addSpawn(KomodusLizard.class, 0.2f);
		addSpawn(IceSprite.class, 0.2f);
		addSpawn(FireSprite.class, 0.2f);
		addSpawn(LightningSprite.class, 0.2f);
	}
}
