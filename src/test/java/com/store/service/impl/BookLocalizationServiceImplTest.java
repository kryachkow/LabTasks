package com.store.service.impl;


import com.store.context.AppContext;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BookLocalizationServiceImplTest {

  @ParameterizedTest
  @CsvSource({
      "test1, test1BT, test1A, test1P, test1PN, test1PR",
      "test2, test2BT, test2A, test2P, test2PN, test2PR"
  })
  void getLocalizedFields(String sourceBundle, String bookTitle, String author, String publisher, String pageNumber, String price)
      throws NoSuchFieldException, IllegalAccessException {
    AppContext.getAppContext().setLocale(new Locale("en"));
    Field field = BookLocalizationServiceImpl.class.getDeclaredField("BUNDLE_LINK");
    field.setAccessible(true);
    BookLocalizationServiceImpl bookLocalizationService = new BookLocalizationServiceImpl();
    field.set(bookLocalizationService, sourceBundle);
    Map<String, String> fields = bookLocalizationService.getLocalizedFields();
    Assertions.assertEquals(bookTitle, fields.get("bookTitle"));
    Assertions.assertEquals(author, fields.get("author"));
    Assertions.assertEquals(publisher, fields.get("publisher"));
    Assertions.assertEquals(pageNumber, fields.get("pageNumber"));
    Assertions.assertEquals(price, fields.get("price"));
  }
}