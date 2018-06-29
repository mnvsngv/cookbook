package com.mnvsngv.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void convertTestListToCsv() {
        List<String> testList = Arrays.asList("One", "Two", "Three");
        String expectedCsv = "One, Two, Three";

        String resultCsv = Utils.convertListToCsv(testList);
        assertEquals(expectedCsv, resultCsv);
    }

    @Test
    public void convertSingleListToCsv() {
        List<String> testList = Collections.singletonList("One");
        String expectedCsv = "One";

        String resultCsv = Utils.convertListToCsv(testList);
        assertEquals(expectedCsv, resultCsv);
    }

    @Test
    public void convertEmptyListToCsv() {
        List<String> testList = new ArrayList<>();
        String expectedCsv = "";

        String resultCsv = Utils.convertListToCsv(testList);
        assertEquals(expectedCsv, resultCsv);
    }

    @Test
    public void convertTestCsvToList() {
        String testCsv = "One, Two, Three";
        List<String> expectedList = Arrays.asList("One", "Two", "Three");

        List<String> resultList = Utils.convertCsvToList(testCsv);
        assertEquals(expectedList, resultList);
    }

    @Test
    public void convertSingleCsvToList() {
        String testCsv = "One";
        List<String> expectedList = Collections.singletonList("One");

        List<String> resultList = Utils.convertCsvToList(testCsv);
        assertEquals(expectedList, resultList);
    }

    @Test
    public void convertEmptyCsvToList() {
        String testCsv = "";

        List<String> resultList = Utils.convertCsvToList(testCsv);
        assertTrue(resultList.isEmpty());
    }
}