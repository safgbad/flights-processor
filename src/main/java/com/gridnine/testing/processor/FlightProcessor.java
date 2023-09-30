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

    /**
     * Filters will be applying in order of declaring in parameters.
     * {@link com.gridnine.testing.filter.NullValuesFlightFilter} applies even if it is not specified in parameters.
     * @param filters array of implementations of {@link com.gridnine.testing.filter.FlightFilter}
     */
    public FlightProcessor(FlightFilter... filters) {
        this.filters = new ArrayList<>();
        this.filters.add(new NullValuesFlightFilter());
        this.filters.addAll(Arrays.asList(filters));
    }

    /**
     * Filters each flight from list in order of filters (as it was specified in constructor parameters).
     * @param flights list of instances of {@link com.gridnine.testing.model.Flight}
     * @return filtered list of flights.
     */
    public List<Flight> process(List<Flight> flights) {
        Predicate<Flight> isFlightAcceptable =
                flight -> filters.stream().allMatch(filter -> filter.isAcceptable(flight));
        return flights.stream()
                .filter(isFlightAcceptable)
                .toList();
    }

}
