package com.kpi.fict.processors;

import com.kpi.fict.dao.DimensionDao;
import com.kpi.fict.dao.FactDao;
import com.kpi.fict.dao.impl.JDBCDaoFactory;
import com.kpi.fict.data.mappers.DataMapper;
import com.kpi.fict.data.mappers.HappinessDataMapper;
import com.kpi.fict.data.mappers.SuicideDataMapper;
import com.kpi.fict.data.mappers.TerrorismDataMapper;
import com.kpi.fict.tables.Fact;
import com.kpi.fict.utils.DimensionsMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadProcessor {
    private DataMapper terrorismDataMapper;
    private DataMapper suicideDataMapper;
    private DataMapper happinessDataMapper;

    public LoadProcessor() {
        this.terrorismDataMapper = new TerrorismDataMapper();
        this.suicideDataMapper = new SuicideDataMapper();
        this.happinessDataMapper = new HappinessDataMapper();
    }

    public void performLoad() throws IOException {
        System.out.println("Loading...");

        System.out.println("Starting parsing of data...");
        List<Fact> terrorismInfo = terrorismDataMapper.getInfo();
        List<Fact> suicideInfo = suicideDataMapper.getInfo();
        List<Fact> happinessInfo = happinessDataMapper.getInfo();
        System.out.println("Parsed data.");

        System.out.println("Starting loading dimension to external DB...");
        loadDimensions();
        System.out.println("Dimension loaded to external DB.");

        System.out.println("Starting join of datasets...");
        List<Fact> totalFacts = joinDataSets(terrorismInfo, suicideInfo, happinessInfo);
        System.out.println("Datasets joined.");

        System.out.println("Starting loading facts to external DB...");
        loadFacts(totalFacts);
        System.out.println("Facts loaded to external DB.");

        System.out.println("Loading performed successfully.");
    }

    private List<Fact> joinDataSets(List<Fact> terrorismInfo, List<Fact> suicideInfo, List<Fact> happinessInfo) {
        List<Fact> result = new ArrayList<>();

        terrorismInfo.forEach(terrorismFact -> suicideInfo.stream()

                .filter(suicideFact ->
                        terrorismFact.getYearValue().equals(suicideFact.getYearValue())
                                &&
                                terrorismFact.getCountryValue().equals(suicideFact.getCountryValue()))

                .forEach(suicideFact -> happinessInfo.stream()

                        .filter(happinessFact ->
                                terrorismFact.getYearValue().equals(happinessFact.getYearValue())
                                        &&
                                        terrorismFact.getCountryValue().equals(happinessFact.getCountryValue()))
                        .forEach(happinessFact -> result.add(Fact.builder()
                                .yearValue(terrorismFact.getYearValue())
                                .monthValue(terrorismFact.getMonthValue())
                                .regionValue(terrorismFact.getRegionValue())
                                .countryValue(terrorismFact.getCountryValue())
                                .cityValue(terrorismFact.getCityValue())
                                .sexValue(suicideFact.getSexValue())
                                .generationValue(suicideFact.getGenerationValue())
                                .ageIntervalValue(suicideFact.getAgeIntervalValue())

                                .suicideNumber(suicideFact.getSuicideNumber())
                                .population(suicideFact.getPopulation())
                                .capitaGDP(suicideFact.getCapitaGDP())
                                .summaryGDP(suicideFact.getSummaryGDP())
                                .countryHDI(suicideFact.getCountryHDI())
                                .terroristsAttacks(terrorismFact.getTerroristsAttacks())
                                .killedInAttacks(terrorismFact.getKilledInAttacks())
                                .happinessScore(happinessFact.getHappinessScore())
                                .freedomScore(happinessFact.getFreedomScore())
                                .trustScore(happinessFact.getTrustScore())

                                .build())
                        )));

        return result;
    }

    private void loadDimensions() {

        DimensionDao dimensionDao = JDBCDaoFactory.getInstance().createDimensionDao();

        DimensionsMapper.getYearDimList()
                .forEach(yearDim -> yearDim.setId((long) dimensionDao.createYearDimension(yearDim)));

        DimensionsMapper.getMonthDimList()
                .forEach(monthDim -> monthDim.setId((long) dimensionDao.createMonthDimension(monthDim)));

        DimensionsMapper.getRegionDimList()
                .forEach(regionDim -> regionDim.setId((long) dimensionDao.createRegionDimension(regionDim)));

        DimensionsMapper.getCountryDimList()
                .forEach(countryDim -> countryDim.setId((long) dimensionDao.createCountryDimension(countryDim)));

        DimensionsMapper.getCityDimList()
                .forEach(cityDim -> cityDim.setId((long) dimensionDao.createCityDimension(cityDim)));

        DimensionsMapper.getSexDimList()
                .forEach(sexDim -> sexDim.setId((long) dimensionDao.createSexDimension(sexDim)));

        DimensionsMapper.getGenerationDimList()
                .forEach(generationDim -> generationDim.setId((long) dimensionDao.createGenerationDimension(generationDim)));

        DimensionsMapper.getAgeIntervalDimList()
                .forEach(ageIntervalDim -> ageIntervalDim.setId((long) dimensionDao.createAgeIntervalDimension(ageIntervalDim)));
    }

    private void loadFacts(List<Fact> facts) {
        FactDao factDao = JDBCDaoFactory.getInstance().createFactDao();

        factDao.createAllFacts(facts);
    }
}
