package com.task4;

import com.task4.controller.Controller;

import java.util.Scanner;

public class ConsoleStore {

    private static final String WELCOME_MESSAGE = "Вітаю в книжковому магазині";

    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(WELCOME_MESSAGE);
        System.out.println(controller.doCommand("/checkCommands"));
        Scanner consoleStore = new Scanner(System.in);

        while(true) {
            String commandName = consoleStore.nextLine().trim();
            System.out.println(controller.doCommand(commandName));
        }
    }


}
