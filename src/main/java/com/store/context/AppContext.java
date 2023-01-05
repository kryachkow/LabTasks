package com.store.context;

import com.store.service.impl.BookGeneratingStrategyServiceImpl;
import com.store.strategy.BookGenerationStrategy;
import java.util.Locale;

public class AppContext {

  private static AppContext instance;
  private BookGenerationStrategy generationStrategy;
  private Locale locale;

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
}
