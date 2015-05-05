package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AIFlight;
import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillFlight;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillArrowShoot;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.weapons.Barehands;
import com.mangolion.epicmangorpg.weapons.LongSword;

public class DungeonEntrance extends Character{

	public DungeonEntrance() {
		super("Dungeon Entrance", "Attack or enter it to enter the dungeon",100, 100, 100, 40, 10, 100, 10, 10,0,0, new Barehands());
		isShop = true;
	}
	
	@Override
	public void openShop() {
		Game.getInstance().currentFloor ++;
		Game.getInstance().begin();
		super.openShop();
	}

}
