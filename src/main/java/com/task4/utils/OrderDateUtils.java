package com.task4.utils;

import com.task4.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;


public class OrderDateUtils {

    private static final String ORDER_BY_DATE_MESSAGE = "Замовлення за датою ";
    private static final String BOOKS_MESSAGE = "Книжки : \n";
    private static final String IN_QUANTITY_MESSAGE = " у кількості ";
    private static final String QT_MESSAGE = " шт. \n";
    private static final String SEPARATOR_MESSAGE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n";
    private static final String DATE_FORMAT_MESSAGE = "dd/MM/yyyy";
    private static final String WRONG_FORMAT_MESSAGE = "Невірний формат дати, будьласка спробуйте ще раз!";
    private static final String ENTER_DATE_TIME_MESSAGE = "Будь-ласка введіть дату та час у форматі 'dd/MM/yyyy HH:mm'!";
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    private OrderDateUtils(){}

    public static String orderToStringMapper(Order order){
        StringBuilder builder = new StringBuilder();
        builder.append(ORDER_BY_DATE_MESSAGE)
                .append(order.getTime())
                .append("\n")
                .append(BOOKS_MESSAGE);
        order.getCart()
                .forEach( cartPart -> builder.append(BookUtils.bookToCustomerString(cartPart.getBook()))
                        .append(IN_QUANTITY_MESSAGE)
                        .append(cartPart.getQuantity())
                        .append(QT_MESSAGE));
        builder.append(SEPARATOR_MESSAGE);
        return builder.toString();
    }

    public static LocalDate getDate(String message){
        LocalDate date = null;
        Scanner dateGetter = new Scanner(System.in);
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(DATE_FORMAT_MESSAGE, Locale.US);
        while (date == null){
            System.out.println(message);
            try {
                date = LocalDate.from(formatterWithTime.parse(dateGetter.nextLine().trim()));
            } catch (DateTimeParseException ignored){
                System.out.println(WRONG_FORMAT_MESSAGE);
            }
        }
        return date;
    }


    public static LocalDateTime getDateTime(){
        Scanner dateGetter = new Scanner(System.in);
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.US);
        LocalDateTime dateTime = null;
        while (dateTime == null){
            System.out.println(ENTER_DATE_TIME_MESSAGE);
            try {
                dateTime = LocalDateTime.from(formatterWithTime.parse(dateGetter.nextLine().trim()));
            } catch (DateTimeParseException ignored){
                System.out.println(WRONG_FORMAT_MESSAGE);
            }
        }
        return dateTime;
    }
}
