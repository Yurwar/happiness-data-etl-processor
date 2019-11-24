package com.kpi.fict.data.mappers.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pair<T, S> {
    private T firstValue;
    private S secondValue;
}
