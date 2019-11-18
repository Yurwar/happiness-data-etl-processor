package com.kpi.fict.data.mappers;

import com.kpi.fict.tables.Fact;
import com.kpi.fict.tables.dimensions.CountryDim;
import com.kpi.fict.tables.dimensions.MonthDim;
import com.kpi.fict.utils.DimensionsMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class TerrorismDataMapper implements DataMapper {

    private final static String YEAR_FIELD = "iyear";
    private final static String MONTH_FIELD = "imonth";
    private final static String COUNTRY_FIELD = "country_txt";
    private final static String REGION_FIELD = "region_txt";
    private final static String CITY_FIELD = "city";
    private final static String KILLED_IN_ATTACK_FIELD = "nkill";


    private static final String DATASET_FILE_PATH = "./src/main/resources/datasource/global-terrorism-rates.csv";

    @Override
    public List<Fact> getInfo() throws IOException {
        return null;
    }

    private CSVParser getParser() throws IOException {
        return CSVParser.parse(new File(DATASET_FILE_PATH), Charset.defaultCharset(), CSVFormat.DEFAULT);
    }

    private List<Fact> extractDataFromDataset(CSVParser csvRecords) {
        List<Fact> resultList = new ArrayList<>();

        csvRecords.forEach(record -> resultList.add(mapDataToEntity(record)));

        return resultList;
    }

    private Fact mapDataToEntity(CSVRecord record) {
        return Fact.builder()
                .countryValue(DimensionsMapper.getCountry(record.get(COUNTRY_FIELD)))
                .yearValue(DimensionsMapper.getYear(record.get(YEAR_FIELD)))
                .monthValue(DimensionsMapper.getMonth(record.get(MONTH_FIELD)))
                .regionValue(DimensionsMapper.getRegion(record.get(REGION_FIELD)))
                .cityValue(DimensionsMapper.getCity(record.get(CITY_FIELD)))
                .killedInAttacks(Integer.parseInt(record.get(KILLED_IN_ATTACK_FIELD)))
                .build();
    }

//    private List<Fact> aggregateByMonth(List<Fact> data) {
//        List<Fact> result = new ArrayList<>();
//
//        Map<CountryDim, List<Fact>> mapByCountry = data.stream()
//                .collect(groupingBy(Fact::getCountryValue, toList()));
//
//
//
//        return result;
//    }

//    private Fact getFactByMonth(Fact fact, List<Fact> data) {
//
//    }
}
