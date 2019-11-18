package com.kpi.fict.utils;

import com.kpi.fict.tables.dimensions.*;

import java.util.HashMap;
import java.util.Map;

public class DimensionsMapper {
    private static Map<String, AgeIntervalDim> ageIntervalDimMap = new HashMap<>();
    private static Map<String, CityDim> cityDimMap = new HashMap<>();
    private static Map<String, CountryDim> countryDimMap = new HashMap<>();
    private static Map<String, GenerationDim> generationDimMap = new HashMap<>();
    private static Map<String, MonthDim> monthDimMap = new HashMap<>();
    private static Map<String, RegionDim> regionDimMap = new HashMap<>();
    private static Map<String, SexDim> sexDimMap = new HashMap<>();
    private static Map<String, YearDim> yearDimMap = new HashMap<>();

    public static AgeIntervalDim getAgeInterval(String name) {
        if (ageIntervalDimMap.containsKey(name)){
            return ageIntervalDimMap.get(name);
        }

        AgeIntervalDim result = new AgeIntervalDim();
        result.setName(name);
        ageIntervalDimMap.put(name, result);

        return result;
    }

    public static CityDim getCity(String name) {
        if (cityDimMap.containsKey(name)){
            return cityDimMap.get(name);
        }

        CityDim result = new CityDim();
        result.setName(name);
        cityDimMap.put(name, result);

        return result;
    }

    public static CountryDim getCountry(String name) {
        if (countryDimMap.containsKey(name)){
            return countryDimMap.get(name);
        }

        CountryDim result = new CountryDim();
        result.setName(name);
        countryDimMap.put(name, result);

        return result;
    }

    public static GenerationDim getGeneration(String name) {
        if (generationDimMap.containsKey(name)){
            return generationDimMap.get(name);
        }

        GenerationDim result = new GenerationDim();
        result.setName(name);
        generationDimMap.put(name, result);

        return result;
    }

    public static MonthDim getMonth(String name) {
        if (monthDimMap.containsKey(name)){
            return monthDimMap.get(name);
        }

        MonthDim result = new MonthDim();
        result.setName(name);
        monthDimMap.put(name, result);

        return result;
    }

    public static RegionDim getRegion(String name) {
        if (regionDimMap.containsKey(name)){
            return regionDimMap.get(name);
        }

        RegionDim result = new RegionDim();
        result.setName(name);
        regionDimMap.put(name, result);

        return result;
    }

    public static SexDim getSex(String name) {
        if (sexDimMap.containsKey(name)){
            return sexDimMap.get(name);
        }

        SexDim result = new SexDim();
        result.setName(name);
        sexDimMap.put(name, result);

        return result;
    }

    public static YearDim getYear(String name) {
        if (yearDimMap.containsKey(name)){
            return yearDimMap.get(name);
        }

        YearDim result = new YearDim();
        result.setName(name);
        yearDimMap.put(name, result);

        return result;
    }
}
