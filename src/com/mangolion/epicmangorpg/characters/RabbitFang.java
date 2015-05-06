package com.mangolion.epicmangorpg.characters;

import com.mangolion.epicmangorpg.ais.AISimple;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.items.Items;
import com.mangolion.epicmangorpg.skills.Skill;
import com.mangolion.epicmangorpg.skills.SkillHealBasic;
import com.mangolion.epicmangorpg.skills.SkillKickBasic;
import com.mangolion.epicmangorpg.skills.SkillSlashBasic;
import com.mangolion.epicmangorpg.skills.SkillBasicSwordCombo;
import com.mangolion.epicmangorpg.skills.SkillBite;
import com.mangolion.epicmangorpg.skills.SkillBlock;
import com.mangolion.epicmangorpg.skills.SkillBodySlam;
import com.mangolion.epicmangorpg.skills.SkillDodge;
import com.mangolion.epicmangorpg.skills.SkillJumpAtk;
import com.mangolion.epicmangorpg.skills.SkillParry;
import com.mangolion.epicmangorpg.skills.SkillSideJump;
import com.mangolion.epicmangorpg.skills.SkillSlashHeavy;
import com.mangolion.epicmangorpg.skills.SkillStab;
import com.mangolion.epicmangorpg.statuses.StatusPoison;
import com.mangolion.epicmangorpg.weapons.Weapon;
import com.mangolion.epicmangorpg.weapons.GreatCopperSword;
import com.mangolion.epicmangorpg.weapons.LongSword;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class RabbitFang extends Character{

	public RabbitFang() {
		super("Fang Rabbit", "A more vicous (but still cute) rabbit with poisoned fangs.",50, 20, 80, 30, 20, 80, 20, 10, 0, 0,new Weapon("Paws", 0, 100, Weapons.BareHand, 1, 1, 1f),   new SkillBlock(), new SkillSideJump(), new SkillJumpAtk());
		addElements(new Element("Furry", 1));
		SkillBite bite = new SkillBite();
		ai = new AISimple(this);
		bite.steps.getFirst().setStatus(new StatusPoison(null, 0.7f), 1);
		addSkills(bite);
		cpBase = 80;
		
		addDrop(Items.rabbitHide, 0.7f, 1);
		addDrop(Items.mstoneSmall, 0.7f, 1);
	}
}
