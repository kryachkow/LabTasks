package com.task7.part2.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.task7.part2.model.NoteBook;
import com.task7.part2.model.PagedItem;
import java.lang.reflect.UndeclaredThrowableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ImmutableObjectHandlerTest {

  private static PagedItem noteBook;
  private static PagedItem immutableNoteBook;

  @BeforeAll
  static void createProxy(){
    noteBook = new NoteBook("goodNoteBook", "someMaterial", 234, 32);
    immutableNoteBook = ImmutableObjectHandler.createImmutableProxy(noteBook);
  }

  @Test
  void getTesting(){
    assertEquals(noteBook.getName(), immutableNoteBook.getName());
    assertEquals(noteBook.getMaterial(), immutableNoteBook.getMaterial());
    assertEquals(noteBook.getPageNumber(), immutableNoteBook.getPageNumber());
    assertEquals(noteBook.getPrice(), immutableNoteBook.getPrice());
  }

  @Test
  void setTesting(){
    assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setName("string"));
    assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setMaterial("string"));
    assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setPrice(54));
    assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setPageNumber(12));
  }

}