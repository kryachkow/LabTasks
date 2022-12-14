package com.task5.part2.utils;

import com.task4.utils.OrderDateUtils;
import com.task5.part2.utils.FilterImpl.ChangeDateFilterImpl;
import com.task5.part2.utils.FilterImpl.ExtensionFilterImpl;
import com.task5.part2.utils.FilterImpl.NameFilterImpl;
import com.task5.part2.utils.FilterImpl.SizeFilterImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChainFilterCreator {
    private static final String ENTER_FIRST_DATE_MESSAGE = "Будь-ласка введіть початкову дату пошуку у форматі 'dd/MM/yyyy'!";
    private static final String ENTER_SECOND_DATE_MESSAGE = "Будь-ласка введіть кінцеву дату пошуку у форматі 'dd/MM/yyyy'!";
    private static final String WRONG_SECOND_DATE_MESSAGE = "Будьласка, введіть кінцеву дату пізніше за початкову!";
    private static final String FIND_BY_FILENAME_CHOOSE_MESSAGE = "Шукати за ім'ям файлу?(0/1)";
    private static final String ENTER_NAME_PART_MESSAGE = "Введіть частину ім'я для пошуку";
    private static final String FIND_BY_EXTENSION_CHOOSE_MESSAGE = "Шукати по розширенню файла?(0/1)";
    private static final String ENTER_EXTENSION_MESSAGE = "Введіть розширення файлу";
    private static final String SEARCH_BY_FILE_SIZE_CHOOSE_MESSAGE = "Шукати по розміру файла?(0/1)";
    private static final String ENTER_MINIMUM_FILESIZE_MESSAGE = "Введіть мінімальний розмір файлу (у мегабайтах)";
    private static final String ENTER_MAXIMUM_FILESIZE_MESSAGE = "Введіть максимальний розмір файлу (у мегабайтах)";
    private static final String CHOOSE_ANOTHER_MAX_SIZE_MESSAGE = "Максимальний розмір має бути більший за мінімальний, перевизначте його!";
    private static final String SEARCH_BY_LAST_CHANGES_DATE_CHOOSE_MESSAGE = "Шукати по даті останніх змін?(0/1)";
    private static final String COMMAND_TO_ADD_MESSAGE  = "1";

    private final List<Filter> filterList;
    private final Scanner fileTreeTraveler;

    public ConsoleChainFilterCreator(){
        fileTreeTraveler = new Scanner(System.in);
        filterList = new ArrayList<>();
    }

    public void addNameFilter(){
        System.out.println(FIND_BY_FILENAME_CHOOSE_MESSAGE);
        if(fileTreeTraveler.nextLine().trim().equals(COMMAND_TO_ADD_MESSAGE)){
            System.out.println(ENTER_NAME_PART_MESSAGE);
            String name = fileTreeTraveler.nextLine().trim();
            filterList.add(new NameFilterImpl(name));
        }
    }

    public void addExtensionFilter(){
        System.out.println(FIND_BY_EXTENSION_CHOOSE_MESSAGE);
        if(fileTreeTraveler.nextLine().trim().equals(COMMAND_TO_ADD_MESSAGE)){
            System.out.println(ENTER_EXTENSION_MESSAGE);
            String extension = fileTreeTraveler.nextLine().trim();
            filterList.add(new ExtensionFilterImpl(extension));
        }
    }

    public void addFileSizeFilter(){
        System.out.println(SEARCH_BY_FILE_SIZE_CHOOSE_MESSAGE);
        if (fileTreeTraveler.nextLine().trim().equals(COMMAND_TO_ADD_MESSAGE)){
            Scanner scanner = new Scanner(System.in);
            System.out.println(ENTER_MINIMUM_FILESIZE_MESSAGE);
            int min = scanner.nextInt();
            System.out.println(ENTER_MAXIMUM_FILESIZE_MESSAGE);
            int max = scanner.nextInt();
            while (max < min){
                System.out.println(CHOOSE_ANOTHER_MAX_SIZE_MESSAGE);
                max = scanner.nextInt();
            }
            filterList.add(new SizeFilterImpl(min, max));
        }
    }

    public void addChangeTimeFilter(){
        System.out.println(SEARCH_BY_LAST_CHANGES_DATE_CHOOSE_MESSAGE);
        if (fileTreeTraveler.nextLine().trim().equals(COMMAND_TO_ADD_MESSAGE)){
            LocalDate firstDate = OrderDateUtils.getDate(ENTER_FIRST_DATE_MESSAGE);
            LocalDate secondDate = OrderDateUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
            while (secondDate.isBefore(firstDate)){
                System.out.println(WRONG_SECOND_DATE_MESSAGE);
                secondDate = OrderDateUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
            }
            filterList.add(new ChangeDateFilterImpl(firstDate, secondDate));
        }
    }

    public ChainFilter getChainFilter(){
        return new ChainFilter(filterList);
    }
}
