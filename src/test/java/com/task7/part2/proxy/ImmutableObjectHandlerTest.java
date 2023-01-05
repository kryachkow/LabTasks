package com.task7.part2.proxy;

import com.task7.part2.model.NoteBook;
import com.task7.part2.model.PagedItem;
import java.lang.reflect.UndeclaredThrowableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    Assertions.assertEquals(noteBook.getName(), immutableNoteBook.getName());
    Assertions.assertEquals(noteBook.getMaterial(), immutableNoteBook.getMaterial());
    Assertions.assertEquals(noteBook.getPageNumber(), immutableNoteBook.getPageNumber());
    Assertions.assertEquals(noteBook.getPrice(), immutableNoteBook.getPrice());
  }

  @Test
  void setTesting(){
    Assertions.assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setName("string"));
    Assertions.assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setMaterial("string"));
    Assertions.assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setPrice(54));
    Assertions.assertThrows(UndeclaredThrowableException.class,  () -> immutableNoteBook.setPageNumber(12));
  }

}