package com.task8.utils.part1.thread;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrimeThreadTest {

  @ParameterizedTest
  @CsvSource( {
      "1, 100, 26",
      "72, 100, 5",
      "22, 42, 5",
      "5, 5, 1",
      "2, 10000, 1229"
  })
  void normalRun(int firstEntrance, int secondEntrance, int resultQuantity) throws InterruptedException {
    PrimeThread primeThread = new PrimeThread(firstEntrance, secondEntrance);
    primeThread.start();
    primeThread.join();
    Assertions.assertEquals(resultQuantity, primeThread.getResultList().size());
  }

  @ParameterizedTest
  @CsvSource( {
      "100, 1",
      "72, 4",
      "5, 1",
      "-3123, -5325",
      "-321, 0"
  })
  void wrongParameters(int firstEntrance, int secondEntrance){
    Assertions.assertThrows(IllegalArgumentException.class, () -> new PrimeThread(firstEntrance, secondEntrance));
  }
}