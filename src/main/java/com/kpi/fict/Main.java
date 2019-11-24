package com.kpi.fict;

import com.kpi.fict.data.mappers.DataMapper;
import com.kpi.fict.data.mappers.HappinessDataMapper;
import com.kpi.fict.data.mappers.SuicideDataMapper;
import com.kpi.fict.data.mappers.TerrorismDataMapper;
import com.kpi.fict.processors.LoadProcessor;
import com.kpi.fict.tables.Fact;
import com.kpi.fict.utils.DimensionsMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LoadProcessor loadProcessor = new LoadProcessor();
        loadProcessor.performLoad();
    }
}
