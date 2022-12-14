package com.task4.command;

import com.task4.service.CatalogService;

public class CatalogCommand implements Command{

    private final CatalogService catalogService;

    public CatalogCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public String doCommand() {
        return catalogService.getCatalog();
    }
}
