package com.task4.command;

import com.task4.model.CartPart;
import com.task4.storage.CartStorage;
import com.task4.storage.OrderStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MakeOrderCommand implements Command{
    private static final String EMPTY_CART_MESSAGE = "Ваша корзина пуста, саме час щось обрати!)";
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    private static final String ENTER_DATE_TIME_MESSAGE = "Будь-ласка введіть дату та час у форматі 'dd/MM/yyyy HH:mm'!";
    private static final String WRONG_DATE_FORMAT_MESSAGE = "Невірний формат дати, будьласка спробуйте ще раз!";
    private static final String ORDER_WAS_MADE_MESSAGE = "Замовлення зроблено!";

    @Override
    public String doCommand() {
        if (CartStorage.isEmpty()){
            return EMPTY_CART_MESSAGE;
        }
        Scanner dateGetter = new Scanner(System.in);
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.US);
        LocalDateTime dateTime = null;
        while (dateTime == null){
            System.out.println(ENTER_DATE_TIME_MESSAGE);
            try {
                dateTime = LocalDateTime.from(formatterWithTime.parse(dateGetter.nextLine().trim()));
            } catch (DateTimeParseException ignored){
                System.out.println(WRONG_DATE_FORMAT_MESSAGE);
            }
        }

        List<CartPart> cart = CartStorage.getEntrySet()
                .stream()
                .map(e -> new CartPart(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        OrderStorage.addOrder(dateTime, cart);
        CartStorage.clear();

        return ORDER_WAS_MADE_MESSAGE;
    }
}
