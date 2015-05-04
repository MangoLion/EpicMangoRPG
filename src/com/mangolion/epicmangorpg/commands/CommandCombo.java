package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import com.mangolion.epicmangorpg.frames.FrameGame;

public abstract class CommandCombo extends JComboBox<Command> implements CommandHandler {
	DefaultComboBoxModel<Command> model = new DefaultComboBoxModel<Command>();
	LinkedList<CommandHandler> previous = new LinkedList<CommandHandler>();
	int selectedIndex = 0;
	
	public CommandCombo(final LinkedList<CommandHandler> previous) {
		setModel(model);
		if (previous != null)
			this.previous.addAll(previous);
		this.previous.add(this);
		refresh();
		addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				selectedIndex = getSelectedIndex();
				CommandHandler next = getSelectedCommand().getNextCommand();
				if (next != null)
					FrameGame.getInstance().setCommand(next);
			}
		});
	}
	
	public void refresh(){
		model.removeAllElements();
		for (Command command: getSubCommands())
			model.addElement(command);
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
		return (Command) getItemAt(selectedIndex);
	}
}
