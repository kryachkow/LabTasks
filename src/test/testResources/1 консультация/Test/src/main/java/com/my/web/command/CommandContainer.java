package com.my.web.command;

import java.util.*;

public class CommandContainer {
	
	private static Map<String, Command> commands; 
	
	static {
		commands = new HashMap<>();
		
		commands.put("login", new LoginCommand());
	}

	public static Command getCommand(String commandName) {
		return commands.get(commandName);
	}

}
