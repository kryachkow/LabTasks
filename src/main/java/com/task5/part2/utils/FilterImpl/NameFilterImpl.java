package com.task5.part2.utils.FilterImpl;

import com.task5.part2.utils.Filter;

import java.io.File;
import java.util.Locale;
import java.util.stream.Stream;

public class NameFilterImpl implements Filter {

  private final String nameFinder;
  private Filter next;

  public NameFilterImpl(String nameToFind) {
    this.nameFinder = nameToFind.toLowerCase(Locale.ROOT);
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
    Stream<File> toRet = files.filter(
        f -> f.getName().toLowerCase(Locale.ROOT).contains(nameFinder));
    return next == null ? toRet : next.filter(toRet);
  }


}
