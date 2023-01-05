package com.task5.part1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtFileReaderTest {
    TxtFileReader txtFileReader;

    @BeforeEach
    void setUp() throws IOException {
        txtFileReader = new TxtFileReader("src/test/testResources/REDAME.txt");
    }

    @Test
    void getRestOfFile() {
        String toRet = "first line \n" +
                "second line is like the first one but second\n" +
                "third line, trinity\n" +
                "line quadro(no joke here \\_0-0_/)\n" +
                "fifth line\n" +
                "no more jokes probably\n" +
                "sixth/seventh line\n" +
                "eightieth line\n" +
                "ninth line";
        Assertions.assertEquals(toRet, txtFileReader.getRestOfFile());
        assertNull(txtFileReader.getRestOfFile());
    }

    @Test
    void getNextLine() {
        List<String> lineList = List.of("first line \n",
                "second line is like the first one but second\n",
                "third line, trinity\n",
                "line quadro(no joke here \\_0-0_/)\n",
                "fifth line\n",
                "no more jokes probably\n",
                "sixth/seventh line\n",
                "eightieth line\n",
                "ninth line");
        for(String line: lineList){
            Assertions.assertEquals(line, txtFileReader.getNextLine());
        }
        assertNull(txtFileReader.getNextLine());
    }

    @Test
    void getLines() {
        String firstToReturn = "first line \n" +
                "second line is like the first one but second\n";
        String secondToReturn = "third line, trinity\n" +
                "line quadro(no joke here \\_0-0_/)\n" +
                "fifth line\n" +
                "no more jokes probably\n" +
                "sixth/seventh line\n" +
                "eightieth line\n";
        String thirdToReturn = "ninth line";

        Assertions.assertEquals(firstToReturn, txtFileReader.getLines(2));
        Assertions.assertEquals(secondToReturn, txtFileReader.getLines(6));
        Assertions.assertEquals(thirdToReturn, txtFileReader.getLines(5));
        assertNull(txtFileReader.getLines(2));
    }
}