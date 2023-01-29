package com.task8.utils.part2.executor;

import com.task8.utils.part2.callable.PrimeCallable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeNumbersExecutorService {

  private final int number;
  private final int threadQuantity;

  public PrimeNumbersExecutorService(int number, int threadQuantity) {
    if (number <= 0 || threadQuantity <= 0) {
      throw new IllegalArgumentException("Thread quantity and last number should be > 0");
    }
    this.number = number;
    this.threadQuantity = Math.min(threadQuantity, number);
  }

  public List<Integer> getPrimeNumbers() throws ExecutionException, InterruptedException {
    List<Future<List<Integer>>> futures = getFutureList();

    return assembleReturnedList(futures);
  }

  private List<Future<List<Integer>>> getFutureList() {
    int entrancePointDivider = Math.floorDiv(number, threadQuantity);
    ExecutorService executorService = Executors.newFixedThreadPool(threadQuantity);
    List<Future<List<Integer>>> futures = new ArrayList<>();

    for (int i = 0; i < threadQuantity - 1; i++) {
      Callable<List<Integer>> callable = new PrimeCallable((i * entrancePointDivider) + 1,
          (i + 1) * entrancePointDivider);
      futures.add(executorService.submit(callable));
    }

    Callable<List<Integer>> lastCallable = new PrimeCallable(futures.size() * entrancePointDivider + 1, number);
    futures.add(executorService.submit(lastCallable));

    executorService.shutdown();

    return futures;
  }

  private List<Integer> assembleReturnedList(List<Future<List<Integer>>> futures)
      throws ExecutionException, InterruptedException {
    List<Integer> toRet = new ArrayList<>();
    for (Future<List<Integer>> future : futures) {
      toRet.addAll(future.get());
    }

    return toRet;
  }

}
