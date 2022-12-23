package com.task5.part2.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainFilter {
    private final Filter firstFilter;
    public ChainFilter(List<Filter> filters){
        for(int i = 0; i < filters.size() - 1; i++){
            filters.get(i).setNext(filters.get(i+1));
        }
        firstFilter = filters.isEmpty() ? null : filters.get(0);
    }

    public List<String> getFileNames(Path path) throws IOException {
        try(Stream<Path> paths = Files.walk(path, FileVisitOption.FOLLOW_LINKS)){
            Stream<File> files = paths.map(Path::toFile);
            return firstFilter != null

                    ? firstFilter
                    .filter(files)
                    .map(File::getAbsolutePath)
                    .collect(Collectors.toList())

                    : files
                    .map(File::getAbsolutePath)
                    .collect(Collectors.toList());
        }
    }
}
