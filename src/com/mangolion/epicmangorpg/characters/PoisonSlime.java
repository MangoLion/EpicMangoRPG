package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.skills.SkillBarrelRoll;
import com.mangolion.epicmangorpg.skills.SkillBasicSlash;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class PoisonSlime extends Character{

	SkillBodySlam bodySlam = new SkillBodySlam();
	public PoisonSlime() {
		super("Poison Slime", "A green blob of poisonous slimes.",40, 0, 100, 30, 10, 100, 10, 10, 0, 0,new Weapon("Slimes", 0, 100, Weapons.BareHand, 1, 1, 1), new SkillBasicSlash(),   new SkillBodySlam(),  new SkillBlock(), new SkillBarrelRoll());
		ai = new AISimple(this);
		addSkills(bodySlam);
		bodySlam.steps.getFirst().setStatus(new StatusPoison(null, 1), 1f);
	}
	
	public PoisonSlime(String name) {
		super(name, "",40, 0, 40, 30, 10, 30, 10, 10, 0, 0,new Weapon("Slimes", 0, 100, Weapons.BareHand, 1, 1, 1), new SkillBasicSlash(), new SkillBodySlam(),    new SkillBlock(), new SkillDodge());
		ai = new AISimple(this);
		addSkills(bodySlam);
		bodySlam.steps.getFirst().setStatus(new StatusPoison(null, 1), 1f);
	}
}
