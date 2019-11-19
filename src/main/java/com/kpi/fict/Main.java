package com.kpi.fict;

import com.kpi.fict.data.mappers.DataMapper;
import com.kpi.fict.data.mappers.HappinessDataMapper;
import com.kpi.fict.data.mappers.SuicideDataMapper;
import com.kpi.fict.data.mappers.TerrorismDataMapper;
import com.kpi.fict.tables.Fact;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        DataMapper dataMapper1 = new TerrorismDataMapper();
        DataMapper dataMapper2 = new HappinessDataMapper();
        DataMapper dataMapper3 = new SuicideDataMapper();

        long startTime = System.currentTimeMillis();

        List<Fact> info1 = dataMapper1.getInfo();
        List<Fact> info2 = dataMapper2.getInfo();
        List<Fact> info3 = dataMapper3.getInfo();

        System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " milliseconds");
        System.out.println("Terrorism: " + info1.size());
        System.out.println("Happiness: " + info2.size());
        System.out.println("Suicide: " + info3.size());
    }
}
