package com.task4.storage;

import com.task4.model.Book;
import com.task4.util.BookUtils;

import java.util.HashMap;

public class CatalogStorage {
    private static final HashMap<String, Book> catalogMap = new HashMap<>();
    static {
        catalogMap.put("Хіба ревуть воли, як ясла повні",
                new Book("Панас Мирний",
                        "Хіба ревуть воли, як ясла повні",
                        "Книграня Є",
                        166,
                        200));
        catalogMap.put("Лісова пісня",
                new Book("Леся Українка",
                        "Лісова пісня",
                        "Фоліо",
                        160,
                        50));
        catalogMap.put("Кобзар",
                new Book("Тарас Шевченко",
                        "Кобзар",
                        "Книжковий клуб",
                        960,
                        250));
        catalogMap.put("Кайдашева сім'я",
                new Book("Іван Нечуй-Левицький",
                        "Кайдашева сім'я",
                        "Фоліо",
                        180,
                        100));
        catalogMap.put("Захар Беркут",
                new Book("Іван Франко",
                        "Захар Беркут",
                        "КСД",
                        256,
                        125));
    }

    public static String getCatalog(){
        StringBuilder builder = new StringBuilder();
        catalogMap.values().forEach(book -> builder.append(BookUtils.bookToCustomerString(book)).append("\n"));
        return builder.toString();
    }

    public static Book getBook(String bookName){
        return catalogMap.getOrDefault(bookName, null);
    }
}
