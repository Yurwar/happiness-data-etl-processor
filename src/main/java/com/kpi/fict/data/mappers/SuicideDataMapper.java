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

public class SuicideDataMapper implements DataMapper {

    private final static String COUNTRY_FIELD = "\uFEFFcountry";
    private final static String YEAR_FIELD = "year";
    private final static String SEX_FIELD = "sex";
    private final static String AGE_FIELD = "age";
    private final static String SUICIDES_NUMBER = "suicides_no";
    private final static String POPULATION_FIELD = "population";
    private final static String HDI_FIELD = "HDI for year";
    private final static String SUMMARY_GDP_FIELD = "gdp_for_year ($)";
    private final static String GDP_PER_CAPITA_FIELD = "gdp_per_capita ($)";
    private final static String GENERATION_FIELD = "generation";

    private static final String DATASET_FILE_PATH = "./src/main/resources/datasource/suicide-rates.csv";

    //27820 entries
    @Override
    public List<Fact> getInfo() throws IOException {
        return extractDataFromDataset(getParser());
    }

    private CSVParser getParser() throws IOException {
        return CSVParser.parse(new File(DATASET_FILE_PATH), StandardCharsets.UTF_8, CSVFormat.newFormat(',')
                .withQuote('"')
                .withHeader()
                .withIgnoreSurroundingSpaces());
    }

    private List<Fact> extractDataFromDataset(CSVParser csvRecords) {
        List<Fact> resultList = new ArrayList<>();

        csvRecords.forEach(record -> resultList.add(mapDataToEntity(record)));

        return resultList;
    }

    private Fact mapDataToEntity(CSVRecord record) {
        float countryHDI;

        try {
            countryHDI = Float.parseFloat(record.get(HDI_FIELD));
        } catch (NumberFormatException e) {
            countryHDI = 0;
        }

        return Fact.builder()
                .countryValue(DimensionsMapper.getCountry(record.get(COUNTRY_FIELD)))
                .yearValue(DimensionsMapper.getYear(record.get(YEAR_FIELD)))
                .sexValue(DimensionsMapper.getSex(record.get(SEX_FIELD)))
                .ageIntervalValue(DimensionsMapper.getAgeInterval(record.get(AGE_FIELD)))
                .generationValue(DimensionsMapper.getGeneration(record.get(GENERATION_FIELD)))
                .suicideNumber(Integer.parseInt(record.get(SUICIDES_NUMBER)))
                .population(Integer.parseInt(record.get(POPULATION_FIELD)))
                .countryHDI(countryHDI)
                .summaryGDP(record.get(SUMMARY_GDP_FIELD))
                .capitaGDP(Float.parseFloat(record.get(GDP_PER_CAPITA_FIELD)))
                .build();
    }
}
