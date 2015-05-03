package com.mangolion.epicmangorpg.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.mangolion.epicmangorpg.characters.Character;
import com.mangolion.epicmangorpg.game.Game;
import com.mangolion.epicmangorpg.skills.Skill;

public class CmdWith extends CommandCombo{

	public CmdWith(LinkedList<CommandHandler> previous) {
		super(previous);
		// TODO Auto-generated constructor stub
	}
	@Override
	public LinkedList<Command> getSubCommands() {
		LinkedList<Command> results = new LinkedList<Command>();
		/*for (Character character: Game.getInstance().charsEnemies){
			results.add(new Command(character.name, Command.COMBOBOX, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text),
							target = Game.getInstance().getCharacter(previous.get(5).getSelectedCommand().text);;
					Skill skill = character.getSkill(previous.get(3).getSelectedCommand().text);
					skill.execute(target);
				}
			}) {
				
			});
		}*/
		final Character character = Game.getInstance().getCharacter(previous.getFirst().getSelectedCommand().text);
		final Skill skill = character.getSkill(previous.get(3).getSelectedCommand().text);
		for (final String str: skill.arguments)
			results.add(new Command(str, Command.COMBOBOX, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					skill.execute(character, str.toLowerCase());
				}
				
			}){
				
			});
		
		return results;
	}
}
