package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdItemOnFor extends CommandCombo {
String item;
	public CmdItemOnFor(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> result = new LinkedList<Command>();
		Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		Item item = character.inventory.getItem(previous.get(3).getSelectedCommand().text).item;
		if (item.hasTarget)
		result.add(new Command("On", Command.COMBOBOX){
			@Override
			public CommandHandler getNextCommand() {
				// TODO Auto-generated method stub
				return new CmdItemOn(previous);
			}
		});
		return result;
	}

}
