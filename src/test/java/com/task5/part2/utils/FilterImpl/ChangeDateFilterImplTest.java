package com.task5.part2.utils.FilterImpl;

import com.task5.part2.utils.Filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChangeDateFilterImplTest {

    @Test
    void filter() throws IOException {
        Filter firstFilter = new ChangeDateFilterImpl(LocalDate.of(2022,1,1),
                LocalDate.of(2022, 10, 11));
        Filter secondFilter = new ChangeDateFilterImpl(LocalDate.of(2022, 11, 11),
                LocalDate.of(2022, 12,14));
        Filter thirdFilter = new ChangeDateFilterImpl(LocalDate.of(2022, 12, 13),
                LocalDate.of(2023, 1, 1));

        Path path = Paths.get("src/test/testResources");

        Stream<File> files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(151,firstFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(10, secondFilter.filter(files).count());

        firstFilter.setNext(secondFilter);
        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(0, firstFilter.filter(files).count());

        files = Files.walk(path, FileVisitOption.FOLLOW_LINKS).map(Path::toFile);
        assertEquals(2, thirdFilter.filter(files).count());
    }
}