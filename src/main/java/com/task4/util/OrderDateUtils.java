package com.task4.util;

import com.task4.model.CartPart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class OrderDateUtils {

    private static final String[] ORDER_TO_STRING_PARTS = new String[]{"Замовлення за датою ", "Книжки : \n", " у кількості ", " шт. \n", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n"};
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String WRONG_FORMAT = "Невірний формат дати, будьласка спробуйте ще раз!";
    public static String orderToStringMapper(Map.Entry<LocalDateTime, List<CartPart>> entry){
        StringBuilder builder = new StringBuilder();
        builder.append(ORDER_TO_STRING_PARTS[0])
                .append(entry.getKey())
                .append("\n")
                .append(ORDER_TO_STRING_PARTS[1]);
        entry.getValue().forEach( cartPart -> builder.append(BookUtils.bookToCustomerString(cartPart.getBook())).append(ORDER_TO_STRING_PARTS[2]).append(cartPart.getQuantity()).append(ORDER_TO_STRING_PARTS[3]));
        builder.append(ORDER_TO_STRING_PARTS[4]);
        return builder.toString();
    }

    public static LocalDate getDate(String message){
        LocalDate date = null;
        Scanner dateGetter = new Scanner(System.in);
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.US);
        while (date == null){
            System.out.println(message);
            try {
                date = LocalDate.from(formatterWithTime.parse(dateGetter.nextLine().trim()));
            } catch (DateTimeParseException ignored){
                System.out.println(WRONG_FORMAT);
            }
        }
        return date;
    }
}
