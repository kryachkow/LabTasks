package com.task4.service.impl;

import com.task4.model.Book;
import com.task4.service.CatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CatalogServiceImplTest {
    CatalogService catalogService;
    @BeforeEach
    void setUp(){
        catalogService = new CatalogServiceImpl();
    }

    @Test
    void getCatalog() {
        Assertions.assertEquals("Порядковий номер книги 1 Книга = Хіба ревуть воли, як ясла повні Автор = Панас Мирний Видавець = Книграня Є Кількість сторінок = 166 Ціна = 200\n" +
                "Порядковий номер книги 2 Книга = Лісова пісня Автор = Леся Українка Видавець = Фоліо Кількість сторінок = 160 Ціна = 50\n" +
                "Порядковий номер книги 3 Книга = Кобзар Автор = Тарас Шевченко Видавець = Книжковий клуб Кількість сторінок = 960 Ціна = 250\n" +
                "Порядковий номер книги 4 Книга = Кайдашева сім'я Автор = Іван Нечуй-Левицький Видавець = Фоліо Кількість сторінок = 180 Ціна = 100\n" +
                "Порядковий номер книги 5 Книга = Захар Беркут Автор = Іван Франко Видавець = КСД Кількість сторінок = 256 Ціна = 125\n",
                catalogService.getCatalog());
    }

    @Test
    void getBook() {
        Assertions.assertEquals(new Book(3L,
                "Тарас Шевченко",
                "Кобзар",
                "Книжковий клуб",
                960,
                250),
                catalogService.getBook(3L));

        Assertions.assertEquals(new Book(5L,
                "Іван Франко",
                "Захар Беркут",
                "КСД",
                256,
                125),
                catalogService.getBook(5L));
    }
}