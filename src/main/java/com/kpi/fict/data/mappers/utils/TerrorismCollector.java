package com.kpi.fict.data.mappers.utils;

import com.kpi.fict.tables.Fact;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class TerrorismCollector implements Collector<Fact, Pair<Integer, Integer>, Pair<Integer, Integer>> {
    @Override
    public Supplier<Pair<Integer, Integer>> supplier() {
        return () -> {
            Pair<Integer, Integer> pair = new Pair<>();

            pair.setFirstValue(0);
            pair.setSecondValue(0);

            return pair;
        };
    }

    @Override
    public BiConsumer<Pair<Integer, Integer>, Fact> accumulator() {
        return (pair, fact) -> {
            pair.setFirstValue(pair.getFirstValue() + 1);
            pair.setSecondValue(pair.getSecondValue() + fact.getKilledInAttacks());
        };
    }

    @Override
    public BinaryOperator<Pair<Integer, Integer>> combiner() {
        return (left, right) -> {
            left.setFirstValue(left.getFirstValue() + right.getFirstValue());
            left.setSecondValue(left.getSecondValue() + right.getSecondValue());
            return left;
        };
    }

    @Override
    public Function<Pair<Integer, Integer>, Pair<Integer, Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
