package com.task5.part2.utils.FilterImpl;

import com.task5.part2.utils.Filter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NameFilterImplTest {

    @Test
    void filter() throws IOException {
        Filter firstFilter = new NameFilterImpl("text");
        Filter secondFilter = new NameFilterImpl("CON");

        Path path = Paths.get("src/test/testResources");
        Stream<File> files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(11, firstFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(15, secondFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        firstFilter.setNext(secondFilter);
        assertEquals(6, firstFilter.filter(files).count());
    }
}