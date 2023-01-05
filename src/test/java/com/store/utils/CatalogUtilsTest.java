package com.store.utils;

import com.store.model.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CatalogUtilsTest {

  static Field pathField;
  static Book book1;
  static Book book2;
  static Book book3;

  @BeforeAll
  static void setUp() throws NoSuchFieldException, IllegalAccessException {
    pathField = CatalogUtils.class.getDeclaredField("SAVED_CATALOG_PATH");
    pathField.setAccessible(true);
    book1 = new Book(1L, "1", "1", "1", 1, 1);
    book2 = new Book(2L, "2", "2", "2", 2, 2);
    book3 = new Book(3L, "3", "3", "3", 3, 3);
  }


  @Test
  void saveCatalog() throws IOException, IllegalAccessException {
    HashMap<Long, Book> mapToSave = new HashMap<Long, Book>();
    mapToSave.put(book1.getId(), book1);
    mapToSave.put(book2.getId(), book2);
    mapToSave.put(book3.getId(), book3);


    pathField.set(null, "src/test/java/com/store/utils/save1");
    CatalogUtils.saveCatalog(mapToSave);

    mapToSave.remove(2L);
    pathField.set(null, "src/test/java/com/store/utils/save2");
    CatalogUtils.saveCatalog(mapToSave);

    mapToSave.remove(3L);
    pathField.set(null, "src/test/java/com/store/utils/save3");
    CatalogUtils.saveCatalog(mapToSave);
  }

  @Test
  void loadCatalog() throws IllegalAccessException {
    pathField.set(null, "src/test/java/com/store/utils/save1");
    HashMap<Long, Book> mapToSave = CatalogUtils.loadCatalog();

    Assertions.assertEquals(3, mapToSave.size());
    Assertions.assertTrue(mapToSave.containsValue(book1));
    Assertions.assertTrue(mapToSave.containsValue(book2));
    Assertions.assertTrue(mapToSave.containsValue(book3));

    pathField.set(null, "src/test/java/com/store/utils/save2");
    mapToSave = CatalogUtils.loadCatalog();

    Assertions.assertEquals(2, mapToSave.size());
    Assertions.assertTrue(mapToSave.containsValue(book1));
    Assertions.assertTrue(mapToSave.containsValue(book3));

    pathField.set(null, "src/test/java/com/store/utils/save3");
    mapToSave = CatalogUtils.loadCatalog();

    Assertions.assertEquals(1, mapToSave.size());
    Assertions.assertTrue(mapToSave.containsValue(book1));

  }

  @Test
  void saveMultipleTimes() {
    HashMap<Long, Book> mapToSave = new HashMap<Long, Book>();
    mapToSave.put(book1.getId(), book1);
    mapToSave.put(book2.getId(), book2);
    mapToSave.put(book3.getId(), book3);

    HashMap<Long, Book> mapToSave2 = new HashMap<Long, Book>();
    mapToSave2.put(book1.getId(), book1);
    mapToSave2.put(book2.getId(), book2);
    mapToSave2.put(book3.getId(), book3);

    HashMap<Long, Book> mapToSave3 = new HashMap<Long, Book>();
    mapToSave3.put(book1.getId(), book1);
    mapToSave3.put(book2.getId(), book2);
    mapToSave3.put(book3.getId(), book3);

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/saveMultiple1");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave2);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/saveMultiple2");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave2);
      objectOutputStream.writeObject(mapToSave3);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/saveSame1");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/saveSame2");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    mapToSave2.clear();
    mapToSave2.put(5L,new Book(5L, "5", "5", "5", 5, 5));
    mapToSave2.put(6L,new Book(6L, "6", "6", "6", 6, 6));
    mapToSave2.put(7L,new Book(7L, "7", "7", "7", 7, 7));

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/saveTwoMapsWithDifferentObjects");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave2);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void gzipsave(){
    HashMap<Long, Book> mapToSave = new HashMap<Long, Book>();
    mapToSave.put(book1.getId(), book1);
    mapToSave.put(book2.getId(), book2);
    mapToSave.put(book3.getId(), book3);

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/compressedSave.gzip");
        GZIPOutputStream gzipOuputStream = new GZIPOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOuputStream);
    ) {
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    HashMap<Long, Book> mapToSave2 = new HashMap<>();
    mapToSave2.put(5L,new Book(5L, "5", "5", "5", 5, 5));
    mapToSave2.put(6L,new Book(6L, "6", "6", "6", 6, 6));
    mapToSave2.put(7L,new Book(7L, "7", "7", "7", 7, 7));

    try (FileOutputStream fileOutputStream = new FileOutputStream(
        "src/test/java/com/store/utils/compressedSave2.gzip");
        GZIPOutputStream gzipOuputStream = new GZIPOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOuputStream);) {
      objectOutputStream.writeObject(mapToSave);
      objectOutputStream.writeObject(mapToSave2);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}