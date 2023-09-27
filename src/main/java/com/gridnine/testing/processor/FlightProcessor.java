package com.gridnine.testing.processor;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.NullValuesFlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FlightProcessor {

    private final List<FlightFilter> filters;

    public FlightProcessor(FlightFilter... filters) {
        this.filters = new ArrayList<>();
        this.filters.add(new NullValuesFlightFilter());
        this.filters.addAll(Arrays.asList(filters));
    }

    public List<Flight> process(List<Flight> flights) {
        Predicate<Flight> isFlightAcceptable =
                flight -> filters.stream().allMatch(filter -> filter.isAcceptable(flight));
        return flights.stream()
                .filter(isFlightAcceptable)
                .toList();
    }

}
