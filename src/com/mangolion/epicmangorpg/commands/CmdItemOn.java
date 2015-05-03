package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdItemOn extends CommandCombo{

	public CmdItemOn(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		for (final Character target: Game.getInstance().getAllChars()){
			results.add(new Command(target.name, Command.COMBOBOX, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
					String item= previous.get(previous.size()-3).getSelectedCommand().text;
							final Skill skill = character.getSkill("Use Item");
					skill.execute(target, item);
				}
			}) {

			});
		}
		
		return results;
	}
}
