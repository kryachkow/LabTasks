package com.task5.part2.utils.FilterImpl;

import com.task5.part2.utils.Filter;

import java.io.File;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ChangeDateFilterImpl implements Filter {

  private final LocalDate firstDate;
  private final LocalDate secondDate;
  private Filter next;

  public ChangeDateFilterImpl(LocalDate firstDate, LocalDate secondDate) {
    if (firstDate.isAfter(secondDate)) {
      throw new IllegalArgumentException();
    }
    this.firstDate = firstDate;
    this.secondDate = secondDate;
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
      LocalDate dateToCheck = LocalDate.ofEpochDay(TimeUnit.MILLISECONDS.toDays(f.lastModified()));
      return dateToCheck.isAfter(firstDate) && dateToCheck.isBefore(secondDate);
    });
    return next == null ? toRet : next.filter(toRet);
  }

}
