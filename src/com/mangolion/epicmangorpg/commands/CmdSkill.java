package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdSkill extends CommandCombo{

	public CmdSkill(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		for (final Skill skill: character.skills){
			results.add(new Command(skill.name, Command.COMBOBOX) {
				
				@Override
				public boolean execute() {
					// TODO Auto-generated method stub
					return skill.execute();
				}
				
				@Override
				public CommandHandler getNextCommand() {
					// TODO Auto-generated method stub
					return new CmdOnWithFor(previous);
				}
			});
		}
		return results;
	}
}
