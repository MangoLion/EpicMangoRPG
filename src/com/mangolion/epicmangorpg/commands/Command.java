package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public abstract class Command{
	public static int COMBOBOX = 0,
			INPUT = 1;
	
	public int type;
	public String text;
	public ActionListener actionListener;
	public Command(String text, int type, ActionListener actionListener){
		this.text = text;
		this.type = type;
		this.actionListener = actionListener;
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
