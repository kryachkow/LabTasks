package com.store.service.impl;

import com.store.model.Book;
import com.store.service.EditableCatalogService;
import com.store.utils.BookUtils;
import com.store.utils.CatalogUtils;
import java.util.HashMap;
import java.util.List;

public class EditableCatalogServiceImpl implements EditableCatalogService {

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


  public EditableCatalogServiceImpl() {
    HashMap<Long, Book> loadedCatalog = CatalogUtils.loadCatalog();
    if (loadedCatalog != null) {
      loadedCatalog.forEach(catalogMap::putIfAbsent);
    }
  }

  @Override
  public String getCatalogToString() {
    StringBuilder builder = new StringBuilder();
    catalogMap.values()
        .forEach(book -> builder.append(BookUtils.bookToCustomerString(book)).append("\n"));
    return builder.toString().trim();
  }

  @Override
  public Book getBook(Long bookId) {
    return catalogMap.getOrDefault(bookId, null);
  }

  @Override
  public List<Book> getAllBooks() {
    return catalogMap.values().stream().toList();
  }

  public HashMap<Long, Book> getCatalog() {
    return catalogMap;
  }

  @Override
  public boolean addBook(Book book) {
    int size = catalogMap.size();
    catalogMap.putIfAbsent(book.getId(), book);
    return catalogMap.size() != size;
  }

  @Override
  public long getNextId() {
    return catalogMap.size() + 1;
  }
}
