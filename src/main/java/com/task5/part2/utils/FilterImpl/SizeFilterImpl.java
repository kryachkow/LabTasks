package com.task5.part2.utils.FilterImpl;

import com.task5.part2.utils.Filter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class SizeFilterImpl implements Filter {

  private final long minSize;
  private final long maxSize;
  private Filter next;

  public SizeFilterImpl(int minSize, int maxSize) {
    this.minSize = minSize * 1000000L;
    this.maxSize = maxSize * 1000000L;
  }

  @Override
  public Filter getNext() {
    return next;
  }

  @Override
  public void setNext(Filter filter) {
    this.next = filter;
  }

  @Override
  public Stream<File> filter(Stream<File> files) {
    Stream<File> toReturn = files.filter(this::appropriateSize);
    return next == null ? toReturn : next.filter(toReturn);
  }

  private boolean appropriateSize(File file) {
    try {
      long size = Files.size(file.toPath());
      return (size > minSize) && (size < maxSize);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
