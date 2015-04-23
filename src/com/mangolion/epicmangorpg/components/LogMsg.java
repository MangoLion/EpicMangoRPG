package com.mangolion.epicmangorpg.components;

import com.mangolion.epicmangorpg.frames.FrameGame;
import com.mangolion.epicmangorpg.game.Game;

public class LogMsg {
	public String text;
	public float time;
	public LogMsg(String text, float time) {
		this.text = text;
		this.time = time;
	}
	static FrameGame frame = FrameGame.instance;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
	public static void addLog(LogMsg log){
		FrameGame.instance.modelLog.addElement(log);
	}
	public static void addLog(String str){
		FrameGame.instance.modelLog.addElement(new LogMsg(str, 	Game.getInstance().timePassed));
	}
	
	public static void appendLog(String str){
		FrameGame.instance.modelLog.get(frame.modelLog.getSize()-1).text += str;
	}
}
