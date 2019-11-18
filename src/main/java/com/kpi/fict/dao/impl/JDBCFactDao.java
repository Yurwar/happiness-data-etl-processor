package com.kpi.fict.dao.impl;

import com.kpi.fict.dao.DaoFactory;
import com.kpi.fict.dao.DimensionDao;
import com.kpi.fict.dao.FactDao;
import com.kpi.fict.db.ConnectionPool;
import com.kpi.fict.tables.Fact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCFactDao implements FactDao {
    private DimensionDao dimensionDao = DaoFactory.getInstance().createDimensionDao();
    private static final String INSERT_FACT_QUERY = "INSERT INTO happiness_facts(year_id,\n" +
            "                            month_id,\n" +
            "                            region_id,\n" +
            "                            country_id,\n" +
            "                            city_id,\n" +
            "                            sex_id,\n" +
            "                            age_interval_id,\n" +
            "                            generation_id,\n" +
            "                            suicide_number,\n" +
            "                            population,\n" +
            "                            hdi_of_country,\n" +
            "                            gdp_summary,\n" +
            "                            gdp_per_capita,\n" +
            "                            happiness_score,\n" +
            "                            freedom_score,\n" +
            "                            trust_score,\n" +
            "                            terrorists_attacks,\n" +
            "                            killed_in_attacks)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void createFact(Fact fact) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACT_QUERY)) {

            fillInsertionFactQuery(preparedStatement, fact);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAllFacts(List<Fact> facts) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACT_QUERY)) {

            for (Fact fact : facts) {
                fillInsertionFactQuery(preparedStatement, fact);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillInsertionFactQuery(PreparedStatement preparedStatement, Fact fact) throws SQLException {
        preparedStatement.setLong(1, dimensionDao.createYearDimension(fact.getYearValue()));
        preparedStatement.setLong(2, dimensionDao.createMonthDimension(fact.getMonthValue()));
        preparedStatement.setLong(3, dimensionDao.createRegionDimension(fact.getRegionValue()));
        preparedStatement.setLong(4, dimensionDao.createCountryDimension(fact.getCountryValue()));
        preparedStatement.setLong(5, dimensionDao.createCityDimension(fact.getCityValue()));
        preparedStatement.setLong(6, dimensionDao.createSexDimension(fact.getSexValue()));
        preparedStatement.setLong(7, dimensionDao.createAgeIntervalDimension(fact.getAgeIntervalValue()));
        preparedStatement.setLong(8, dimensionDao.createGenerationDimension(fact.getGenerationValue()));
        preparedStatement.setInt(9, fact.getSuicideNumber());
        preparedStatement.setInt(10, fact.getPopulation());
        preparedStatement.setDouble(11, fact.getCountryHDI());
        preparedStatement.setString(12, fact.getSummaryGDP());
        preparedStatement.setDouble(13, fact.getCapitaGDP());
        preparedStatement.setDouble(14, fact.getHappinessScore());
        preparedStatement.setDouble(15, fact.getFreedomScore());
        preparedStatement.setDouble(16, fact.getTrustScore());
        preparedStatement.setInt(17, fact.getTerroristsAttacks());
        preparedStatement.setInt(18, fact.getKilledInAttacks());
    }
}
