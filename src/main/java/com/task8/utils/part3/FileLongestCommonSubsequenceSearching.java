package com.task8.utils.part3;

import com.task8.utils.part3.thread.RepeatingSequenceSearchThread;
import java.io.IOException;
import java.lang.Thread.State;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileLongestCommonSubsequenceSearching {

  private static final String ENTER_FILE_PATH = "Введіть шлях до файлу";

  public static void main(String[] args) {
    System.out.println(ENTER_FILE_PATH);
    Scanner scanner = new Scanner(System.in);
    RepeatingSequenceSearchThread repeatingSequenceSearchThread = null;
    while (repeatingSequenceSearchThread == null) {
      String path = scanner.nextLine();
      try {
        repeatingSequenceSearchThread = new RepeatingSequenceSearchThread(Paths.get(path));
      } catch (IOException e) {
        System.out.println(ENTER_FILE_PATH);
      }
    }
    repeatingSequenceSearchThread.start();

    while (true) {
      if (repeatingSequenceSearchThread.getState() == State.WAITING) {
        System.out.println(ENTER_FILE_PATH);
        String path = scanner.nextLine();
        try {
          repeatingSequenceSearchThread.setAnotherPath(Paths.get(path));
        } catch (IOException | InterruptedException e) {
          System.out.println(ENTER_FILE_PATH);
        }
      }
    }
  }
}
