package com.task4.controller;

import com.task4.command.Command;

public class Controller {

    private static final String WRONG_COMMAND_MESSAGE = "Ця команда відсутня або не працює, спробуйте ввести іншу!";

    public String doCommand(String commandName){
        Command command = CommandContainer.getCommand(commandName);
        if(command != null) {
            return command.doCommand();
        }
        return WRONG_COMMAND_MESSAGE;
    }
}
