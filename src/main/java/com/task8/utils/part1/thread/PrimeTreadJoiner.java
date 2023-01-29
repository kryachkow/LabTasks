package com.task8.utils.part1.thread;

import java.util.ArrayList;
import java.util.List;

public class PrimeTreadJoiner {

  private final int number;
  private final int threadQuantity;

  public PrimeTreadJoiner(int number, int threadQuantity) {
    if (number <= 0 || threadQuantity <= 0) {
      throw new IllegalArgumentException("Thread quantity and last number should be > 0");
    }
    this.number = number;
    this.threadQuantity = Math.min(threadQuantity, number);
  }

  public List<Integer> getPrimeNumbers() throws InterruptedException {
    List<PrimeThread> primeThreads = createThreadList();
    startThreads(primeThreads);

    return assembleReturnedList(primeThreads);
  }

  private List<PrimeThread> createThreadList() {
    int entrancePointDivider = Math.floorDiv(number, threadQuantity);
    List<PrimeThread> primeTradList = new ArrayList<>();
    for (int i = 0; i < threadQuantity - 1; i++) {
      primeTradList.add(
          new PrimeThread((i * entrancePointDivider) + 1, (i + 1) * entrancePointDivider));
    }
    primeTradList.add(
        new PrimeThread(primeTradList.size() * entrancePointDivider + 1, number));

    return primeTradList;
  }

  private void startThreads(List<PrimeThread> primeThreadList) throws InterruptedException {
    for (PrimeThread primeThread : primeThreadList) {
      primeThread.start();
    }
    for (PrimeThread primeThread : primeThreadList) {
      primeThread.join();
    }
  }

  private List<Integer> assembleReturnedList(List<PrimeThread> primeThreadList) {
    List<Integer> toRet = new ArrayList<>();
    for (PrimeThread primeThread : primeThreadList) {
      toRet.addAll(primeThread.getResultList());
    }

    return toRet;
  }
}
