package com.mangolion.epicmangorpg.commands;

import java.util.LinkedList;

public abstract interface CommandHandler {
	LinkedList<CommandHandler> getPrevious();
	LinkedList<Command> getSubCommands();
	Command getSelectedCommand();
}
