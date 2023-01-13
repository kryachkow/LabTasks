package com.task8.utils.part3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ByteSequence {

  private List<Byte> sequence;
  private List<Integer> entrances;

  public ByteSequence(List<Byte> bytes, List<Integer> entrances) {
    sequence = bytes;
    this.entrances = entrances;
  }

  public List<Byte> getSequence() {
    return sequence;
  }

  public void setSequence(List<Byte> sequence) {
    this.sequence = sequence;
  }

  public List<Integer> getEntrances() {
    return entrances;
  }

  public void setEntrances(List<Integer> entrances) {
    this.entrances = entrances;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ByteSequence)) {
      return false;
    }
    ByteSequence that = (ByteSequence) o;
    return Objects.equals(getSequence(), that.getSequence()) && Objects.equals(
        getEntrances(), that.getEntrances());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSequence(), getEntrances());
  }
}
