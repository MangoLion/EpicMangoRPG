package com.mangolion.epicmangorpg.commands;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public abstract class CommandInput extends JTextField implements CommandHandler {
	LinkedList<CommandHandler> previous = new LinkedList<CommandHandler>();
	
	
	public CommandInput(LinkedList<CommandHandler> previous) {
		super(5);
		if (previous != null)
			this.previous.addAll(previous);
		this.previous.add(this);
		setPreferredSize(new Dimension(100,20));
	}
	
	@Override
	public LinkedList<CommandHandler> getPrevious() {
		// TODO Auto-generated method stub
		return previous;
	}

	@Override
	public LinkedList<Command> getSubCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Command getSelectedCommand() {
		// TODO Auto-generated method stub
		return null;
	}

}
