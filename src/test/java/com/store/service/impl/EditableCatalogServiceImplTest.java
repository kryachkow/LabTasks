package com.store.service.impl;

import com.store.model.Book;
import com.store.service.EditableCatalogService;
import java.lang.reflect.Field;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditableCatalogServiceImplTest {

  EditableCatalogService editableCatalogService;
  Map<Long, Book> catalogMap;

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    editableCatalogService = new EditableCatalogServiceImpl();
    Field catalogMapField = EditableCatalogServiceImpl.class.getDeclaredField("catalogMap");
    catalogMapField.setAccessible(true);
    catalogMap = (Map<Long, Book>) catalogMapField.get(editableCatalogService);
    catalogMap.clear();
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

  @Test
  void getCatalogToString() {
    Assertions.assertEquals(
        "Порядковий номер книги 1 Книга = Хіба ревуть воли, як ясла повні Автор = Панас Мирний Видавець = Книграня Є Кількість сторінок = 166 Ціна = 200\n"
            +
            "Порядковий номер книги 2 Книга = Лісова пісня Автор = Леся Українка Видавець = Фоліо Кількість сторінок = 160 Ціна = 50\n"
            +
            "Порядковий номер книги 3 Книга = Кобзар Автор = Тарас Шевченко Видавець = Книжковий клуб Кількість сторінок = 960 Ціна = 250\n"
            +
            "Порядковий номер книги 4 Книга = Кайдашева сім'я Автор = Іван Нечуй-Левицький Видавець = Фоліо Кількість сторінок = 180 Ціна = 100\n"
            +
            "Порядковий номер книги 5 Книга = Захар Беркут Автор = Іван Франко Видавець = КСД Кількість сторінок = 256 Ціна = 125\n",
        editableCatalogService.getCatalogToString());
  }

  @Test
  void getBook() {
    Assertions.assertEquals(new Book(3L,
            "Тарас Шевченко",
            "Кобзар",
            "Книжковий клуб",
            960,
            250),
        editableCatalogService.getBook(3L));

    Assertions.assertEquals(new Book(5L,
            "Іван Франко",
            "Захар Беркут",
            "КСД",
            256,
            125),
        editableCatalogService.getBook(5L));
  }

  @Test
  void getNextId(){
    Assertions.assertEquals(catalogMap.size() + 1, editableCatalogService.getNextId());
  }

  @Test
  void addBook(){
    Book bookToAdd=  new Book(6L,
        "Квітка-Основ'яненко Григорій",
        "Маруся",
        "Видав",
        32,
        1);
    Book thatNotAdd =  new Book(6L,
        "Для цього завдання ",
        "Доводиться згадувати",
        "Всю шкільну українську літературу",
        0,
        0);

    Assertions.assertTrue(editableCatalogService.addBook(bookToAdd));
    Assertions.assertTrue(catalogMap.containsValue(bookToAdd));


    Assertions.assertFalse(editableCatalogService.addBook(thatNotAdd));
    Assertions.assertFalse(catalogMap.containsValue(thatNotAdd));
  }
}