package com.task4.service.impl;

import com.task4.model.Book;
import com.task4.service.CatalogService;
import com.task4.utils.BookUtils;

import java.util.HashMap;

public class CatalogServiceImpl implements CatalogService {
    private final HashMap<Long, Book> catalogMap = new HashMap<>();
     {
        catalogMap.put(1L,
                new Book(1L,
                        "Панас Мирний",
                        "Хіба ревуть воли, як ясла повні",
                        "Книграня Є",
                        166,
                        200));
        catalogMap.put(2L,
                new Book(2L,
                        "Леся Українка",
                        "Лісова пісня",
                        "Фоліо",
                        160,
                        50));
        catalogMap.put(3L,
                new Book(3L,
                        "Тарас Шевченко",
                        "Кобзар",
                        "Книжковий клуб",
                        960,
                        250));
        catalogMap.put(4L,
                new Book(4L,
                        "Іван Нечуй-Левицький",
                        "Кайдашева сім'я",
                        "Фоліо",
                        180,
                        100));
        catalogMap.put(5L,
                new Book(5L,
                        "Іван Франко",
                        "Захар Беркут",
                        "КСД",
                        256,
                        125));
    }

    public CatalogServiceImpl(){}

    @Override
    public String getCatalog(){
        StringBuilder builder = new StringBuilder();
        catalogMap.values().forEach(book -> builder.append(BookUtils.bookToCustomerString(book)).append("\n"));
        return builder.toString();
    }

    @Override
    public Book getBook(Long bookId){
        return catalogMap.getOrDefault(bookId, null);
    }
}
