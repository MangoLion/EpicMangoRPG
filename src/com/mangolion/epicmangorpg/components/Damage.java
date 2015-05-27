package com.mangolion.epicmangorpg.components;

import java.util.Arrays;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.statuses.Buff;
import com.mangolion.epicmangorpg.statuses.Status;

public class Damage {
	public static int MELEE = 0, RANGE = 1, MAGIC = 2;	
	public Character source;
	public float amount, barrierNegation = 0;
	public int type;
	public LinkedList<Element> elements = new LinkedList<Element>();
	public LinkedList<Status> statuses = new LinkedList<Status>();
	public LinkedList<Buff> buffs = new LinkedList<Buff>();
	
	public Damage(Character source, float amount, int type, Element ... elements) {
		this.source = source;
		this.amount = amount;
		this.type = type;
		if (elements != null)
			this.elements.addAll( Arrays.asList(elements));
	}
	
	public Damage(Character source, float amount, int type, float negate, Element ... elements) {
		this.source = source;
		this.amount = amount;
		this.type = type;
		barrierNegation = negate;
		if (elements != null)
			this.elements.addAll( Arrays.asList(elements));
	}
	
	public Damage setStatuses(LinkedList<Status> statuses){
		this.statuses.addAll(statuses);
		return this;
	}
	
	public Damage setStatuses(Status ... statuses){
		this.statuses.addAll(Arrays.asList(statuses));
		return this;
	}
	
	public Damage setBuffs(Buff ... buffs){
		this.buffs.addAll(Arrays.asList(buffs));
		return this;
	}
	
	public Damage setBuffs(LinkedList<Buff> buffs){
		this.buffs.addAll(buffs);
		return this;
	}
}
