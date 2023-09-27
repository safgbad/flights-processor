package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class NullValuesFlightFilter implements FlightFilter {

    private boolean isSegmentOrBoundsOfSegmentNull(Segment segment) {
        if (segment == null) {
            return true;
        }
        LocalDateTime leftBound = segment.getDepartureDate();
        LocalDateTime rightBound = segment.getArrivalDate();
        return leftBound == null || rightBound == null;
    }

    @Override
    public boolean isAcceptable(Flight flight) {
        if (flight == null) {
            return false;
        }
        List<Segment> segments = flight.getSegments();
        return segments.stream().noneMatch(this::isSegmentOrBoundsOfSegmentNull);
    }

}
