package com.kpi.fict.tables;

import com.kpi.fict.tables.dimensions.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fact {
    private Long factId;
    private YearDim yearValue;
    private MonthDim monthValue;
    private RegionDim regionValue;
    private CountryDim countryValue;
    private CityDim cityValue;
    private SexDim sexValue;
    private AgeIntervalDim ageIntervalValue;
    private GenerationDim generationValue;

    private int suicideNumber;
    private int population;
    private float countryHDI;
    private String summaryGDP;
    private float capitaGDP;
    private float happinessScore;
    private float freedomScore;
    private float trustScore;
    private int terroristsAttacks;
    private int killedInAttacks;
}
