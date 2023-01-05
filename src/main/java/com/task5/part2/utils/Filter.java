package com.task5.part2.utils;

import java.io.File;
import java.util.stream.Stream;

public interface Filter {
    public Filter getNext();
    public void setNext(Filter filter);
    public Stream<File> filter(Stream<File> files);

}
