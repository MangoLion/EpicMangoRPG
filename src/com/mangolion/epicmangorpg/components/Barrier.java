package com.mangolion.epicmangorpg.components;

import java.util.Arrays;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.characters.CharacterPlayer;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.game.Utility;
import com.mangolion.epicmangorpg.messages.Msg;

public class Barrier implements StatBuff{
	public static final int MELEE = 0, RANGE = 1, MAGIC = 2, ALL = 3;
	
	public Character character;
	public String name;
	public float hp, maxHp, time, absorbPercent = 1,  def,  magicDef,prot;
	public int type = ALL;
	public LinkedList<Element> elements = new LinkedList<Element>();
	
	public Barrier(Character character, String name, float hp, float def, float magicDef,float prot, float time, float absorbPercent, Element ... elements) {
		this.character = character;
		this.name = name;
		this.hp = hp;
		maxHp = hp;
		this.time = time;
		this.def = def;
		this.magicDef = magicDef;
		this.prot = prot;
		this.absorbPercent = absorbPercent;
		if (elements != null)
			this.elements.addAll(Arrays.asList(elements));
	}
	
	public Barrier(Character character, String name, float hp, float def, float magicDef,float prot, float time, float absorbPercent, int type, Element ... elements) {
		this.character = character;
		this.name = name;
		this.hp = hp;
		maxHp = hp;
		this.time = time;
		this.def = def;
		this.magicDef = magicDef;
		this.prot = prot;
		this.absorbPercent = absorbPercent;
		this.type = type;
		if (elements != null)
			this.elements.addAll(Arrays.asList(elements));
	}
	
	public float setDamage(Damage damage){
		if (type != 3 && damage.type != type){
			System.out.println("not correct");
			return damage.amount;
		}
		
		Character source = damage.source;
		System.out.println("{" + damage.amount);
		float cdmg = damage.amount * absorbPercent*(1 - damage.barrierNegation),
				ldmg;
		if (cdmg > (hp + def)*1.2f)
			cdmg = (hp + def)*1.2f;
		 ldmg = damage.amount - cdmg;
		 damage.amount = ldmg;
		 System.out.println( damage.amount+ "}, hp:" + hp); 
			if (damage.type == Damage.MELEE || damage.type == Damage.RANGE)
				 cdmg = (cdmg - def)*(100 - prot)/100;
			else
				cdmg = (cdmg - magicDef)*(100 - prot)/100;
		cdmg *= Elements.calculate(damage.elements, getElements());
		
		cdmg = (cdmg <=0 )? 1:cdmg;
		float change = Style.positive(source, character, Style.dmg, cdmg, 1 - source.getAccuracy(character));
		StylePainter.append(new Msg(false, " $targetname's "+ name + " absorbed $num damage").getMessage(source, character, cdmg), Style.getSegments(change, source));
		LogMsg.addLog(source + " dealt " + String.valueOf(cdmg) + " damage to " + name);
		hp -= cdmg;
		return ldmg;
	}

	private LinkedList<Element> getElements() {
		// TODO Auto-generated method stub
		return elements;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " (" + hp/maxHp*100+ "%)";
	}

	@Override
	public float getHPBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMPBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSPBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBalBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHPRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMPRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSPRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBalRegenBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSPCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBalCostBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDexBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getIntBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getStrBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAgiBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDefBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getProtBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccuracyBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCriticalBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeleeDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getGunDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCylinderDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBowDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMagicDmgBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeleeSpeedBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMagicSpeedBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMagicDefBuff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBarrierNegate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
