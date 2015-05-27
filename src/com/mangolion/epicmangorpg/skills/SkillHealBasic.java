package com.mangolion.epicmangorpg.skills;

import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.components.ActionType;
import com.mangolion.epicmangorpg.components.Element;
import com.mangolion.epicmangorpg.components.Elements;
import com.mangolion.epicmangorpg.components.LogMsg;
import com.mangolion.epicmangorpg.components.Proficiency;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.steps.Step;
import com.mangolion.epicmangorpg.weapons.Weapons;

public class SkillHealBasic extends Skill {

	public SkillHealBasic() {
		super("Heal", "Basic healing magic",Weapons.ALL, ActionType.RecoverHP);
		setObservable(true, 0.7f);
		addSteps(new Step(this, "Basic Heal", "",ActionType.Magic, 0.5f, 0.3f, 0f,0){
			@Override
			public float getDamage() {
				// TODO Auto-generated method stub
				return super.getDamage();
			}
			
			float healRate = 50;
			@Override
			public void execute(Character target) {
				if (target.isAllied != getCharacter().isAllied)
					target = Game.getInstance().findAlly(getCharacter());
				target.setHeal(character, (healRate + getCharacter().getIntDamage()*prof));
				addProf(new Proficiency(getCharacter(), target));
				setObservable(true, 0.7f);
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
			
			@Override
			public float getIntBuff() {
				
				return Utility.format4(prof*10f);
			}
		}.setElement(new Element("light", 1))
		.setCost(0, 15, 0, 0));
		
	}

}
