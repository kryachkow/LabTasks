package com.task5.part2.utils.FilterImpl;

import com.task5.part2.utils.Filter;

import java.io.File;
import java.util.stream.Stream;

public class ExtensionFilterImpl implements Filter {

  private final String extension;
  private Filter next;

  public ExtensionFilterImpl(String extension) {
    this.extension = extension;
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
    Stream<File> toRet = files.filter(f -> {
      String name = f.getName();
      return name.substring(name.indexOf(".") + 1).equals(extension);
    });
    return next == null ? toRet : next.filter(toRet);
  }
}
