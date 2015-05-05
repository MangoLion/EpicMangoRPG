package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.items.Item;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdItem extends CommandCombo{

	public CmdItem(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		final Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		final Skill skill = character.getSkill("Use Item");
		for (final String str: skill.getArguments())
			results.add(new Command(str, Command.COMBOBOX){
				
				@Override
				public boolean execute() {
					return skill.execute(character, str.toLowerCase());
				}
				
				@Override
				public CommandHandler getNextCommand() {
					System.out.println("created next");
					return new CmdItemOnFor(previous);
				}
			});
		
		return results;
	}
	
}
