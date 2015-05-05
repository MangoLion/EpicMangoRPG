package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public abstract class Command{
	public static int COMBOBOX = 0,
			INPUT = 1;
	
	public int type;
	public String text;
	public Command(String text, int type){
		this.text = text;
		this.type = type;
	}
	
	public boolean execute(){
		return false;
	}
	
	public CommandHandler getNextCommand(){
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
}
