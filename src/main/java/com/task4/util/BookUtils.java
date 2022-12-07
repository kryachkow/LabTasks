package com.task4.util;

import com.task4.model.Book;

public class BookUtils {

    private static final String[] BOOK_TO_STRING_PARTS = new String[]{"Книга = ", " Автор = ", " Видавець = ", " Кількість сторінок = ", " Ціна = "};

    public static String bookToCustomerString(Book book){
        return BOOK_TO_STRING_PARTS[0] + book.getBookTitle()
                + BOOK_TO_STRING_PARTS[1] + book.getAuthor()
                + BOOK_TO_STRING_PARTS[2] + book.getPublisher()
                + BOOK_TO_STRING_PARTS[3] + book.getPageNumber()
                + BOOK_TO_STRING_PARTS[4] + book.getPrice();
    }
}
