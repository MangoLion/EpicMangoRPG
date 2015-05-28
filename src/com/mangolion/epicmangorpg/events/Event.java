package com.mangolion.epicmangorpg.events;

import java.util.Random;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.game.StylePainter;
import com.mangolion.epicmangorpg.messages.Msg;
import com.mangolion.epicmangorpg.steps.Step;

public abstract class Event implements Cloneable {
	public String name, desc;
	public Character source, target;
	public boolean hitAllEnemies = false, hitAll = false;
	public float time;
	public Msg msgExecute;
	public Random rand = new Random();
	public Step step;
	
	public Event(String name, String desc, float time, Character souce, Character target, Msg msg, Step step) {
		this.step =step;
		this.name = name;
		this.desc = desc;
		this.time = time;
		this.source = souce;
		this.target = target;
		this.msgExecute = msg;
	}
	
	public void tick(float dtime){
		time = (float) (Math.round((time - dtime) * 100d) / 100d);
	}
	
	public void execute(){
		if (msgExecute != null)
			StylePainter.append(msgExecute.getMessage(source, target, 0));
		//else
		//	StylePainter.append(new Msg(source.name + "'s " + name + " to " + target.name + " has completed.").getMessage(null, null, 0));
	}
	
	public static void addEvent(Event event){
		Game.getInstance().events.add(event);
		FrameGame.instance.tabInfo.setSelectedIndex(1);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (target != null)
			return name + " to " + target.name + ": " + time;
		else 
			return name + "will be cancelled in " + time;
	}
	
	public Event copy(){
		try {
			return (Event) clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
