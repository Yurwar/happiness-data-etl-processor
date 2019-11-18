package com.kpi.fict.dao.impl;

import com.kpi.fict.dao.DaoFactory;
import com.kpi.fict.dao.DimensionDao;
import com.kpi.fict.dao.FactDao;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public FactDao createFactDao() {
        return new JDBCFactDao();
    }

    @Override
    public DimensionDao createDimensionDao() {
        return new JDBCDimensionDao();
    }
}
