package com.mangolion.epicmangorpg.floors;

import com.mangolion.epicmangorpg.characters.AdventurerNewb;
import com.mangolion.epicmangorpg.characters.RangeArcher;
import com.mangolion.epicmangorpg.characters.BirdGiant;
import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.DungeonEntrance;
import com.mangolion.epicmangorpg.characters.Instructor;
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
import com.mangolion.epicmangorpg.game.Terrain;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.weapons.Weapon;

public class FloorTrainning extends Floor {
	Skill skill;
	Weapon weapon;
	Item ammo;
	public FloorTrainning(Skill skill, Weapon weapon, Item ammo) {
		terrains.add(Terrain.Plain);
		this.skill = skill;
		this.weapon = weapon;
		this.ammo = ammo;
		addSpawn(Dummy.class, 0.2f);
		addSpawn(Dummy.class, 0.2f);
		addSpawn(Dummy.class, 0.2f);
	}
	@Override
	public Character getAlly() {
		Character instructor = new Instructor();
		instructor.equip(weapon);
		instructor.skills.clear();
		instructor.addSkills(skill);
		instructor.inventory.addItem(ammo, 1000);
		return instructor;
	}
}
