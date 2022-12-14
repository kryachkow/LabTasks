package com.task5.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TxtFileReader {
    private final List<String> lineList;

    public TxtFileReader(String path) throws IOException {
        Path constructorPath = Paths.get(path);
        lineList = Files.newBufferedReader(constructorPath).lines().collect(Collectors.toList());
    }

    public String getRestOfFile() {
        return readWithForLoop(lineList.size());
    }

    public String getNextLine() {
        return readWithForLoop(1);
    }

    public String getLines(int lineNumber) {
        return readWithForLoop(lineNumber);
    }

    private String readWithForLoop(int lineNumber) {
        if(lineList.isEmpty()){
            return null;
        }

        if(lineNumber > lineList.size()){
            lineNumber = lineList.size();
        }
        StringBuilder builder = new StringBuilder();
        List<String> returnList = new ArrayList<>();
        for(int i = 0; i < lineNumber; i++){
            returnList.add(lineList.get(i));
        }
        lineList.removeAll(returnList);
        returnList.forEach(r -> builder.append(r).append("\n"));
        if(lineList.isEmpty()){
           return builder.toString().trim();
        }
        return builder.toString();
    }


}
