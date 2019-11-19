package com.kpi.fict.data.mappers;

import com.kpi.fict.tables.Fact;
import com.kpi.fict.tables.dimensions.*;
import com.kpi.fict.utils.DimensionsMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class TerrorismDataMapper implements DataMapper {

    private final static String YEAR_FIELD = "iyear";
    private final static String MONTH_FIELD = "imonth";
    private final static String COUNTRY_FIELD = "country_txt";
    private final static String REGION_FIELD = "region_txt";
    private final static String CITY_FIELD = "city";
    private final static String KILLED_IN_ATTACK_FIELD = "nkill";


    private static final String DATASET_FILE_PATH = "./src/main/resources/datasource/global-terrorism-rates.csv";

    //107548 entries
    @Override
    public List<Fact> getInfo() throws IOException {
        return extractDataFromDataset(getParser());
    }

    private CSVParser getParser() throws IOException {
        return CSVParser.parse(new File(DATASET_FILE_PATH), StandardCharsets.UTF_8, CSVFormat.newFormat(',')
                .withHeader()
                .withQuote('"')
                .withIgnoreSurroundingSpaces());
    }

    private List<Fact> extractDataFromDataset(CSVParser csvRecords) {
        List<Fact> resultList = new ArrayList<>();

        csvRecords.forEach(record -> resultList.add(mapDataToEntity(record)));

        return aggregateByDimensions(resultList);
    }

    private Fact mapDataToEntity(CSVRecord record) {
        int killed;

        try {
            killed = Integer.parseInt(record.get(KILLED_IN_ATTACK_FIELD));
        } catch (NumberFormatException e) {
            killed = 0;
        }

        return Fact.builder()
                .yearValue(DimensionsMapper.getYear(record.get(YEAR_FIELD)))
                .monthValue(DimensionsMapper.getMonth(record.get(MONTH_FIELD)))
                .regionValue(DimensionsMapper.getRegion(record.get(REGION_FIELD)))
                .countryValue(DimensionsMapper.getCountry(record.get(COUNTRY_FIELD)))
                .cityValue(DimensionsMapper.getCity(record.get(CITY_FIELD)))
                .killedInAttacks(killed)
                .build();
    }

    private List<Fact> aggregateByDimensions(List<Fact> data) {
        List<Fact> result = new ArrayList<>();

        Map<YearDim, Map<MonthDim, Map<RegionDim, Map<CountryDim, Map<CityDim, Integer>>>>> fuckingMap = data.parallelStream()
                .collect(groupingBy(Fact::getYearValue,
                        groupingBy(Fact::getMonthValue,
                                groupingBy(Fact::getRegionValue,
                                        groupingBy(Fact::getCountryValue,
                                                groupingBy(Fact::getCityValue,
                                                        Collectors.summingInt(Fact::getKilledInAttacks)))))));

        fuckingMap.keySet()
                .forEach(yearKey -> fuckingMap.get(yearKey).keySet()
                        .forEach(monthKey -> fuckingMap.get(yearKey).get(monthKey).keySet()
                                .forEach(regionKey -> fuckingMap.get(yearKey).get(monthKey).get(regionKey).keySet()
                                        .forEach(countryKey -> fuckingMap.get(yearKey).get(monthKey).get(regionKey).get(countryKey).keySet()
                                                .forEach(cityKey -> {
                                                    Fact fact = Fact.builder()
                                                            .yearValue(yearKey)
                                                            .monthValue(monthKey)
                                                            .regionValue(regionKey)
                                                            .countryValue(countryKey)
                                                            .cityValue(cityKey)
                                                            .killedInAttacks(fuckingMap.get(yearKey).get(monthKey).get(regionKey).get(countryKey).get(cityKey))
                                                            .build();

                                                    result.add(fact);
                                                })))));

        fuckingMap.get(new YearDim());

        return result;
    }
}
