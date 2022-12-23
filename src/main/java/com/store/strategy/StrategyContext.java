package com.store.strategy;

import com.store.strategy.impl.ConsoleBookGeneration;
import com.store.strategy.impl.RandomBookGeneration;
import java.util.Locale;
import java.util.Scanner;

public class StrategyContext {

  private static final String RANDOM_CHOOSE_KEY = "RAN";
  private static final String CONSOLE_CHOOSE_KEY = "CON";
  private static final String CHOOSE_BOOK_GENERATING_METHOD = "Оберіть вид генерації книг "
      + RANDOM_CHOOSE_KEY
      + " для випадкової, "
      + CONSOLE_CHOOSE_KEY
      + " для конслольної";

  private BookGenerationStrategy strategy;

  public StrategyContext() {
    Scanner scanner = new Scanner(System.in);
    String resp;
    while (strategy == null) {
      System.out.println(CHOOSE_BOOK_GENERATING_METHOD);
      resp = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
      if (resp.equals(RANDOM_CHOOSE_KEY)) {
        strategy = new RandomBookGeneration();
      }
      if (resp.equals(CONSOLE_CHOOSE_KEY)) {
        strategy = new ConsoleBookGeneration();
      }
    }
  }

  public BookGenerationStrategy getStrategy() {
    return strategy;
  }

}
