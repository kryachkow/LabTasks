package com.store.service.impl;

import com.store.service.BookGeneratingStrategyService;
import com.store.strategy.BookGenerationStrategy;
import com.store.strategy.impl.ConsoleBookGeneration;
import com.store.strategy.impl.ConsoleLocalizedBookGeneration;
import com.store.strategy.impl.RandomBookGeneration;
import com.store.strategy.impl.RandomLocalizedBookGeneration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class BookGeneratingStrategyServiceImpl implements BookGeneratingStrategyService {

  private static final String RANDOM_CHOOSE_KEY = "RAN";
  private static final String CONSOLE_CHOOSE_KEY = "CON";
  private static final String RANDOM_LOCALIZED_CHOOSE_KEY = "RANL";
  private static final String CONSOLE_LOCALIZED_CHOOSE_KEY = "CONL";
  private static final String CHOOSE_BOOK_GENERATING_METHOD = "Оберіть вид генерації книг "
      + RANDOM_CHOOSE_KEY
      + " для випадкової, "
      + CONSOLE_CHOOSE_KEY
      + " для конслольної "
      + RANDOM_LOCALIZED_CHOOSE_KEY
      + " для випадкової локалізованої "
      + CONSOLE_LOCALIZED_CHOOSE_KEY
      + " для консольної локалізованої";

  private Map<String, BookGenerationStrategy> strategyMap;

  {
    strategyMap = new HashMap<>();
    strategyMap.put(RANDOM_CHOOSE_KEY, new RandomBookGeneration());
    strategyMap.put(CONSOLE_CHOOSE_KEY, new ConsoleBookGeneration());
    strategyMap.put(RANDOM_LOCALIZED_CHOOSE_KEY,
        new RandomLocalizedBookGeneration(new BookLocalizationServiceImpl()));
    strategyMap.put(CONSOLE_LOCALIZED_CHOOSE_KEY,
        new ConsoleLocalizedBookGeneration(new BookLocalizationServiceImpl()));
  }

  public BookGeneratingStrategyServiceImpl() {
  }

  @Override
  public BookGenerationStrategy getStrategy() {
    Scanner scanner = new Scanner(System.in);
    BookGenerationStrategy strategy = null;
    String resp;
    while (strategy == null) {
      System.out.println(CHOOSE_BOOK_GENERATING_METHOD);
      resp = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
      strategy = strategyMap.get(resp.toUpperCase());
    }
    return strategy;
  }

}
