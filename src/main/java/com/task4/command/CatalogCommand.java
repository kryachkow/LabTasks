package com.task4.command;

import com.task4.storage.CatalogStorage;

public class CatalogCommand implements Command{
    @Override
    public String doCommand() {
        return CatalogStorage.getCatalog();
    }
}
