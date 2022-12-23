package com.store.utils;

import com.store.model.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CatalogUtils {

  private static String SAVED_CATALOG_PATH
      = "src/main/resources/com/consoleStore/utils/catalogSave.txt";
  private static final String DIDNT_SAVED_CATALOG_EXCEPTION_MESSAGE = "Could`t save the catalog";

  private CatalogUtils() {
  }


  public static HashMap<Long, Book> loadCatalog() {
    try (FileInputStream fileInputStream = new FileInputStream(SAVED_CATALOG_PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
      return (HashMap<Long, Book>) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return null;
    }
  }

  public static void saveCatalog(HashMap<Long, Book> catalog) throws IOException {
    File file = new File(SAVED_CATALOG_PATH);
    if (!file.exists()) {
      file.createNewFile();
    }
    try (FileOutputStream fileOutputStream = new FileOutputStream(SAVED_CATALOG_PATH);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(catalog);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(DIDNT_SAVED_CATALOG_EXCEPTION_MESSAGE, e);
    }
  }

}
