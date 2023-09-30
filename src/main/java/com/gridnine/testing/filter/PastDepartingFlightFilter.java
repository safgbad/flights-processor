package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Filters flights that depart in the past (first segment departure is before an instance of filter initialization).
 */
public class PastDepartingFlightFilter implements FlightFilter {

    private final LocalDateTime currentDateTime;

    public PastDepartingFlightFilter() {
        this.currentDateTime = LocalDateTime.now();
    }

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        Segment firstSegment = segments.get(0);
        LocalDateTime departureDateTime = firstSegment.getDepartureDate();
        return departureDateTime.isAfter(currentDateTime);
    }

}
