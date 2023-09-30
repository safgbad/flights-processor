package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;
import java.util.Objects;

/**
 * Filters null-valued flights and flights with null-valued segments.
 */
public class NullValuesFlightFilter implements FlightFilter {

    @Override
    public boolean isAcceptable(Flight flight) {
        if (flight == null) {
            return false;
        }
        List<Segment> segments = flight.getSegments();
        return segments.stream().noneMatch(Objects::isNull);
    }

}
