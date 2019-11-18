package com.kpi.fict.data.mappers;

import com.kpi.fict.tables.Fact;

import java.io.IOException;
import java.util.List;

public interface DataMapper {
    List<Fact> getInfo() throws IOException;
}
