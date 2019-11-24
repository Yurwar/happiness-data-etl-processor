package com.kpi.fict.dao.impl;

import com.kpi.fict.dao.DimensionDao;
import com.kpi.fict.dao.exception.DaoException;
import com.kpi.fict.db.ConnectionPool;
import com.kpi.fict.tables.dimensions.*;

import java.sql.*;
import java.util.NoSuchElementException;

public class JDBCDimensionDao implements DimensionDao {

    private static final String INSERT_INTO_AGE_INTERVAL_DIM_NAME_VALUES = "insert into age_interval_dim(name) values (?)";
    private static final String INSERT_INTO_CITY_DIM_NAME_VALUES = "insert into city_dim(name) values (?)";
    private static final String INSERT_INTO_COUNTRY_DIM_NAME_VALUES = "insert into country_dim(name) values (?)";
    private static final String INSERT_INTO_GENERATION_DIM_NAME_VALUES = "insert into generation_dim(name) values (?)";
    private static final String INSERT_INTO_MONTH_DIM_NAME_VALUES = "insert into month_dim(name) values (?)";
    private static final String INSERT_INTO_REGION_DIM_NAME_VALUES = "insert into region_dim(name) values (?)";
    private static final String INSERT_INTO_SEX_DIM_NAME_VALUES = "insert into sex_dim(name) values (?)";
    private static final String INSERT_INTO_YEAR_DIM_NAME_VALUES = "insert into year_dim(name) values (?)";

    @Override
    public int createAgeIntervalDimension(AgeIntervalDim ageIntervalDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_AGE_INTERVAL_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, ageIntervalDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("age_interval_id", "age_interval_dim", ageIntervalDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createCityDimension(CityDim cityDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CITY_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cityDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("city_id", "city_dim", cityDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createCountryDimension(CountryDim countryDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_COUNTRY_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, countryDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("country_id", "country_dim", countryDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createGenerationDimension(GenerationDim generationDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GENERATION_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, generationDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("generation_id", "generation_dim", generationDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createMonthDimension(MonthDim monthDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_MONTH_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, monthDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("month_id", "month_dim", monthDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createRegionDimension(RegionDim regionDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_REGION_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, regionDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("region_id", "region_dim", regionDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createSexDimension(SexDim sexDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SEX_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, sexDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("sex_id", "sex_dim", sexDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public int createYearDimension(YearDim yearDim) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_YEAR_DIM_NAME_VALUES,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, yearDim.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new DaoException();
            }

        } catch (SQLException e) {
            if (isUniqueConstraintException(e)) {
                return getIdOfExistingDimensionName("year_id", "year_dim", yearDim.getName());
            } else {
                throw new DaoException(e);
            }
        }
    }

    private boolean isUniqueConstraintException(SQLException e) {
        return e.getSQLState().equals("23505");
    }

    private int getIdOfExistingDimensionName(String idColumnName, String dimTableName, String name) {
        String selectIdQuery = String.format("select %s from %s where name = ?", idColumnName, dimTableName);
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectIdQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new NoSuchElementException(String.format("Cant find id of dimension %s with name %s", dimTableName, name));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }
}
