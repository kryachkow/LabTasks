package com.store.server.client.factory.impl;

import static org.mockito.Mockito.mock;

import com.store.model.Book;
import com.store.service.CatalogService;
import com.store.service.EditableCatalogService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractServerTests {

  static CatalogService catalogService = mock(EditableCatalogService.class);

  private final ClientSideApplicationDummy clientSideApplicationDummy = new ClientSideApplicationDummy();
  static int port;

  @ParameterizedTest
  @MethodSource("getNoSuchCommandTestArguments")
  void notSuchCommandResponseTest(String request, String response) throws IOException {

    Assertions.assertEquals(response,
        clientSideApplicationDummy.getResponseFromServer(request, port));
  }


  @ParameterizedTest
  @MethodSource("getCountCountCommandTestArguments")
  void countCommandTest(String request, String response, int listSize) throws IOException {
    List<Book> mockedList = mock(List.class);
    Mockito.when(catalogService.getAllBooks()).thenReturn(mockedList);
    Mockito.when(mockedList.size()).thenReturn(listSize);
    Assertions.assertEquals(response,
        clientSideApplicationDummy.getResponseFromServer(request, port));

  }

  @ParameterizedTest
  @MethodSource("getInfoItemTestArguments")
  void itemInfoTest(String request, String response, Long id, String name, int price) throws IOException {
    Book mockedBook = mock(Book.class);
    Mockito.when(catalogService.getBook(id)).thenReturn(mockedBook);
    Mockito.when(mockedBook.getBookTitle()).thenReturn(name);
    Mockito.when(mockedBook.getPrice()).thenReturn(price);
    Assertions.assertEquals(response,
        clientSideApplicationDummy.getResponseFromServer(request, port));
  }


  @ParameterizedTest
  @MethodSource("getNoSuchItemArguments")
  void noSuchItem(String request, String response) throws IOException {
    Mockito.when(catalogService.getBook(Mockito.anyLong())).thenReturn(null);
    Assertions.assertEquals(response,
        clientSideApplicationDummy.getResponseFromServer(request, port));
  }


  abstract Stream<Arguments> getNoSuchCommandTestArguments();

  abstract Stream<Arguments> getCountCountCommandTestArguments();

  abstract Stream<Arguments> getInfoItemTestArguments();
  abstract Stream<Arguments> getNoSuchItemArguments();


}
