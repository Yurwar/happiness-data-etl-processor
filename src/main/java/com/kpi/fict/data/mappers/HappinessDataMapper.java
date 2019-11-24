package com.kpi.fict.data.mappers;

import com.kpi.fict.tables.Fact;
import com.kpi.fict.utils.DimensionsMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HappinessDataMapper implements DataMapper {
    private final static String COUNTRY_FIELD = "Country";
    private final static String HAPPINESS_SCORE_FIELD = "Happiness Score";
    private final static String HAPPINESS_SCORE_FIELD_FOR_2017 = "Happiness.Score";
    private final static String FREEDOM_SCORE_FIELD = "Freedom";
    private final static String TRUST_SCORE_FIELD = "Trust (Government Corruption)";
    private final static String TRUST_SCORE_FIELD_FOR_2017 = "Trust..Government.Corruption.";

    private static final String DATASET_FILE_PATH_YEAR_2015 = "./src/main/resources/datasource/world-happiness/world-happiness-2015.csv";
    private static final String DATASET_FILE_PATH_YEAR_2016 = "./src/main/resources/datasource/world-happiness/world-happiness-2016.csv";
    private static final String DATASET_FILE_PATH_YEAR_2017 = "./src/main/resources/datasource/world-happiness/world-happiness-2017.csv";

    private static final String YEAR_2015 = "2015";
    private static final String YEAR_2016 = "2016";
    private static final String YEAR_2017 = "2017";

    // 470
    @Override
    public List<Fact> getInfo() throws IOException {
        List<Fact> resultFacts = new ArrayList<>();
        resultFacts.addAll(extractDataFromDataset(getParser(DATASET_FILE_PATH_YEAR_2015), YEAR_2015));
        resultFacts.addAll(extractDataFromDataset(getParser(DATASET_FILE_PATH_YEAR_2016), YEAR_2016));
        resultFacts.addAll(extractDataFromDataset(getParser(DATASET_FILE_PATH_YEAR_2017), YEAR_2017));
        return resultFacts;
    }

    private CSVParser getParser(String path) throws IOException {
        return CSVParser.parse(new File(path), StandardCharsets.UTF_8, CSVFormat.newFormat(',')
                .withQuote('"')
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces());
    }

    private List<Fact> extractDataFromDataset(CSVParser csvRecords, String year) {
        List<Fact> resultList = new ArrayList<>();

        csvRecords.forEach(record -> resultList.add(mapDataToEntity(record, year)));

        return resultList;
    }

    private Fact mapDataToEntity(CSVRecord record, String year) {
        return Fact.builder()
                .countryValue(DimensionsMapper.getCountry(record.get(COUNTRY_FIELD)))
                .yearValue(DimensionsMapper.getYear(year))
                .happinessScore(Float.parseFloat(year.equals(YEAR_2017) ? record.get(HAPPINESS_SCORE_FIELD_FOR_2017) : record.get(HAPPINESS_SCORE_FIELD)))
                .freedomScore(Float.parseFloat(record.get(FREEDOM_SCORE_FIELD)))
                .trustScore(Float.parseFloat(year.equals(YEAR_2017) ? record.get(TRUST_SCORE_FIELD_FOR_2017) : record.get(TRUST_SCORE_FIELD)))
                .build();
    }
}
