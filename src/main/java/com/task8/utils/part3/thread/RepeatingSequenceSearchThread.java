package com.task8.utils.part3.thread;

import com.task8.utils.part3.model.ByteSequence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.ArrayUtils;

public class RepeatingSequenceSearchThread extends Thread {

  private final InnerSearchThread innerSearchThread;


  public RepeatingSequenceSearchThread(Path path) throws IOException {
    innerSearchThread = this.new InnerSearchThread(path);
    innerSearchThread.setDaemon(true);
  }

  public void setAnotherPath(Path path) throws IOException, InterruptedException {
    while (this.getState() != State.WAITING) {
      sleep(300);
    }
    innerSearchThread.setBytes(path);
    innerSearchThread.setLongestSequence(
        new ByteSequence(new ArrayList<Byte>(), new ArrayList<Integer>()));
    synchronized (innerSearchThread) {
      innerSearchThread.notify();
    }
    synchronized (this) {
      this.notify();
    }
  }

  @Override
  public void run() {
    innerSearchThread.start();
    while (this.getState() == State.RUNNABLE) {
      synchronized (innerSearchThread) {
        innerSearchThread.notify();
      }
      runChecker();
      seeResult();
    }
  }

  private void runChecker() {
    while (innerSearchThread.getState() == State.RUNNABLE) {
      try {
        sleep(100);
        System.out.println(
            "Current max length is " + innerSearchThread.getLongestSequence().getSequence().size());
      } catch (InterruptedException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
  }

  private void seeResult() {
    System.out.println("Search is completed result is size of the biggest repeating sequence is  "
        + innerSearchThread.getLongestSequence().getSequence().size() + " entrances are"
        + innerSearchThread.getLongestSequence().getEntrances());
    try {
      synchronized (this) {
        this.wait();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }


  private class InnerSearchThread extends Thread {

    private ByteSequence longestSequence;
    private List<Byte> bytes;

    private InnerSearchThread(Path path) throws IOException {
      bytes = new ArrayList<>(List.of(ArrayUtils.toObject(Files.readAllBytes(path))));
      longestSequence = new ByteSequence(new ArrayList<Byte>(), new ArrayList<Integer>());
    }


    @Override
    public void run() {
      while (this.getState() == State.RUNNABLE) {
        toIterate();
        try {
          synchronized (this) {
            this.wait();
          }
        } catch (InterruptedException e) {
          throw new RuntimeException(e.getMessage(), e);
        }

      }
    }

    private void toIterate() {
      int iterationSize = bytes.size();
      HashMap<List<Byte>, List<Integer>> sequences = new HashMap<>();

      for (int i = 0; i < iterationSize; i++) {

        for (int j = i + longestSequence.getSequence().size() + 1;
            j < iterationSize && j < i + iterationSize / 2; j++) {

          if (!Objects.equals(bytes.get(j), bytes.get(j - 1))) {
            List<Byte> sequence = bytes.subList(i, j);
            if (sequences.containsKey(sequence)) {

              if (longestSequence.getSequence().size() < sequence.size()) {
                longestSequence.setSequence(sequence);
                longestSequence.setEntrances(sequences.get(sequence));
                longestSequence.getEntrances().add(i);
              }
            } else {
              sequences.put(sequence, new ArrayList<>(List.of(i)));
            }
          }
        }
      }
    }

    private ByteSequence getLongestSequence() {
      return longestSequence;
    }

    private void setLongestSequence(ByteSequence longestSequence) {
      this.longestSequence = longestSequence;
    }

    private void setBytes(Path path) throws IOException {
      this.bytes = new ArrayList<>(List.of(ArrayUtils.toObject(Files.readAllBytes(path))));
    }
  }

}
