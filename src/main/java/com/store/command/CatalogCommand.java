package com.store.command;

import com.store.service.CatalogService;

public class CatalogCommand implements Command {

  private final CatalogService catalogService;

  public CatalogCommand(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @Override
  public String doCommand() {
    return catalogService.getCatalogToString();
  }
}
