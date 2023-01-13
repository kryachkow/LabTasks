package com.task8.utils.part1.thread;

import java.util.ArrayList;
import java.util.List;

public class PrimeThread extends Thread {

  private final int firstEntrance;
  private final int lastEntrance;
  private final List<Integer> resultList;

  public PrimeThread(int firstEntrance, int lastEntrance) {
    if (lastEntrance < firstEntrance) {
      throw new IllegalArgumentException("First entrance should be >= than last entrance");
    }
    if (firstEntrance < 0) {
      throw new IllegalArgumentException("Entrances should be > 0");
    }
    this.firstEntrance = firstEntrance;
    this.lastEntrance = lastEntrance;
    resultList = new ArrayList<>();
  }

  @Override
  public void run() {
    for (int i = firstEntrance; i <= lastEntrance; i++) {
      primeCheck(i);
    }
  }

  public List<Integer> getResultList() {
    return resultList;
  }

  private void primeCheck(int numberToCheck) {
    for (int checker = numberToCheck - 1; checker >= 2; checker--) {
      if (numberToCheck % checker == 0) {
        return;
      }
    }
    resultList.add(numberToCheck);
  }
}
