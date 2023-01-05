package com.task5.part2;

import com.task5.part2.utils.ConsoleChainFilterCreator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileTreeTraveler {

    public static final String ENTER_PATHS_MESSAGE = "Увведіть шлях для огляду файлового дерева!";
    private static final String YOUR_RESULT_MESSAGE = "Ось результат пошуку";

    public static void main(String[] args) throws IOException {

        System.out.println(ENTER_PATHS_MESSAGE);
        Scanner pathGetter = new Scanner(System.in);
        String pathUri = pathGetter.nextLine().trim();

        ConsoleChainFilterCreator consoleChainFilterCreator = new ConsoleChainFilterCreator();
        consoleChainFilterCreator.addNameFilter();
        consoleChainFilterCreator.addExtensionFilter();
        consoleChainFilterCreator.addFileSizeFilter();
        consoleChainFilterCreator.addChangeTimeFilter();

        System.out.println(YOUR_RESULT_MESSAGE);
        List<String> pathsList = consoleChainFilterCreator.getChainFilter().getFileNames(Paths.get(pathUri));
        pathsList.forEach(System.out::println);

    }
}
