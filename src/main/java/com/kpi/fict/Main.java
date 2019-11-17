package com.kpi.fict;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        CSVParser csvParser = null;

        try {
            csvParser = CSVParser.parse(new File("./src/main/resources/datasource/suicide-rates.csv"), Charset.defaultCharset(), CSVFormat.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (CSVRecord record : csvParser) {
            for (String el : record) {
                System.out.print(el + "\t");
            }
            System.out.println();
        }
    }
}
