package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.SkillBarrelRoll;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SlimePoison extends Character{

	SkillBodySlam bodySlam = new SkillBodySlam();
	public SlimePoison() {
		super("Poison Slime", "A green blob of poisonous slimes.",40, 0, 100, 30, 10, 100, 10, 10, 0, 0,new Weapon("Slimes", 0, 100, Weapons.BareHand, 1, 1, 1), new SkillSlashBasic(),   new SkillBodySlam(),  new SkillBlock(), new SkillBarrelRoll());
		addElements(new Element("Plant", 1));
		ai = new AISimple(this);
		addSkills(bodySlam);
		bodySlam.steps.getFirst().setStatus(new StatusPoison(null, 1), 1f);
		
		addDrop(Items.slime, 08f, 1);
		addDrop(Items.mstoneSmall, 0.6f, 1);
	}

}
