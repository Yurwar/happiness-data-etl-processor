package com.kpi.fict.dao;

import com.kpi.fict.tables.Fact;

import java.util.List;

public interface FactDao {
    void createFact(Fact fact);

    void createAllFacts(List<Fact> facts);
}
