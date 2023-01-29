package com.store.context;

import com.store.server.ServerThread;
import com.store.server.client.factory.impl.HTTPClientThreadFactory;
import com.store.server.client.factory.impl.TCPClientThreadFactory;
import com.store.service.CatalogService;
import com.store.strategy.BookGenerationStrategy;
import java.io.IOException;
import java.util.Locale;

public final class AppContext {

  private static final String SERVERS_ARE_ALREADY_STARTED_ERROR_MESSAGE = "Cервера вже запущені";
  private static final String SERVERS_CANNOT_BE_STARTED = "Помилка при запуску серверів, причина: ";

  private static AppContext instance;
  private BookGenerationStrategy generationStrategy;
  private Locale locale;
  private ServerThread TCPServer;
  private ServerThread HTTPServer;

  private AppContext() {
  }

  public BookGenerationStrategy getGenerationStrategy() {
    return generationStrategy;
  }

  public void setGenerationStrategy(BookGenerationStrategy generationStrategy) {
    this.generationStrategy = generationStrategy;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public static AppContext getAppContext() {
    if (instance == null) {
      instance = new AppContext();
    }
    return instance;
  }

  public void startServers(CatalogService catalogService) {

    if (TCPServer != null || HTTPServer != null) {
      System.out.println(SERVERS_ARE_ALREADY_STARTED_ERROR_MESSAGE);
      return;
    }
    try {
      this.TCPServer = new ServerThread(3000, new TCPClientThreadFactory(catalogService));
      this.HTTPServer = new ServerThread(4000, new HTTPClientThreadFactory(catalogService));
      TCPServer.start();
      HTTPServer.start();
    } catch (IOException e) {
      System.out.println(SERVERS_CANNOT_BE_STARTED + e.getMessage());
    }

  }

}
