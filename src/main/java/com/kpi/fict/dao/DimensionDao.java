package com.kpi.fict.dao;

import com.kpi.fict.tables.dimensions.*;

public interface DimensionDao {
    int createAgeIntervalDimension(AgeIntervalDim ageIntervalDim);

    int createCityDimension(CityDim cityDim);

    int createCountryDimension(CountryDim countryDim);

    int createGenerationDimension(GenerationDim generationDim);

    int createMonthDimension(MonthDim monthDim);

    int createRegionDimension(RegionDim regionDim);

    int createSexDimension(SexDim sexDim);

    int createYearDimension(YearDim yearDim);
}
