package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillBasicHeal extends Skill {

	public SkillBasicHeal() {
		super("Heal", "Basic healing magic",Weapons.ALL, ActionType.RecoverHP);
		addSteps(new Step(this, "Basic Heal", "",ActionType.Magic, 0.7f, 0.5f, 0.2f, 0, 20, 0, 5,0){
			@Override
			public float getDamage() {
				// TODO Auto-generated method stub
				return super.getDamage();
			}
			
			float healRate = 40;
			@Override
			public void execute(Character target) {
				target.setHeal(character, healRate);
				addProf(new Proficiency(getCharacter(), target));
				setObservable(true, 0.5f);
				super.execute(target);
			}
			
			@Override
			public void addProf(Proficiency p) {
				float add = 0;
				if (p.target.getHp() < p.target.getMaxHP())
					if (p.target.getHp() < p.target.getMaxHP() * 20)
						add += 0.02;
					else
						add += 0.01;
				LogMsg.appendLog(", \n " + name + " gained " + add + "% proficiency" );
				//super.addProf(p);
			}
		});
		
	}

}
