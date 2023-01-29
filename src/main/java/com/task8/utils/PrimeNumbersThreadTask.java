package com.task8.utils;

import com.task8.utils.part2.executor.PrimeNumbersExecutorService;
import com.task8.utils.part1.thread.PrimeTreadJoiner;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class PrimeNumbersThreadTask {

  private static final String ENTER_NUMBER = "Введіть число";
  private static final String ENTER_QUANTITY = "Введіть кількість";
  private static final String NUMBERS_ONLY = "Тільки числа > 0";

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Scanner scanner = new Scanner(System.in);

    int number = getIntFromConsole(scanner, ENTER_NUMBER);
    int quantity = getIntFromConsole(scanner, ENTER_QUANTITY);

    System.out.println("PrimeThreadJoiner Result");
    long startTime = System.nanoTime();
    PrimeTreadJoiner primeTreadJoiner = new PrimeTreadJoiner(number, quantity);
    System.out.println(primeTreadJoiner.getPrimeNumbers());
    long executionTime = System.nanoTime() - startTime;
    System.out.println("PrimeThreadJoiner Result is " + executionTime);

    System.out.println("PrimeNumberExecutorService Result");
    startTime = System.nanoTime();
    PrimeNumbersExecutorService primeNumbersExecutorService = new PrimeNumbersExecutorService(
        number, quantity);
    System.out.println(primeNumbersExecutorService.getPrimeNumbers());
    executionTime = System.nanoTime() - startTime;
    System.out.println("PrimeNumberExecutorService Result is " + executionTime);
  }



  private static int getIntFromConsole(Scanner scanner, String enterQuantity) {
    int quantity = 0;

    System.out.println(enterQuantity);
    do {
      try {
        quantity = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println(NUMBERS_ONLY);
        scanner.next();
      }
    } while (quantity <= 0);

    return quantity;
  }

}

