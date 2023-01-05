package com.store.command;

import com.store.service.EditableCatalogService;
import com.store.utils.CatalogUtils;
import java.io.IOException;

public class ExitCommand implements Command {
  private static final String EXIT_MESSAGE = "Дякую що завітали в наш магазин!";
  EditableCatalogService editableCatalogService;

  public ExitCommand(EditableCatalogService editableCatalogService) {
    this.editableCatalogService = editableCatalogService;
  }

  @Override
  public String doCommand() {
    try {
      CatalogUtils.saveCatalog(editableCatalogService.getCatalog());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(EXIT_MESSAGE);
    System.exit(0);
    return null;
  }
}
