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

class SizeFilterImplTest {

    @Test
    void filter() throws IOException {
        Filter firstFilter = new SizeFilterImpl(0,1);
        Filter secondFilter = new SizeFilterImpl(1,2);
        Filter thirdFilter = new SizeFilterImpl(2, 4);

        Path path = Paths.get("src/test/testResources");
        Stream<File> files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(86, firstFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(1, secondFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        firstFilter.setNext(secondFilter);
        assertEquals(0, firstFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(1, thirdFilter.filter(files).count());
    }
}