package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class SegmentArrivingBeforeDepartureFlightFilter implements FlightFilter {

    private boolean isArrivalBeforeDeparture(Segment segment) {
        LocalDateTime departure = segment.getDepartureDate();
        LocalDateTime arrival = segment.getArrivalDate();
        return arrival.isBefore(departure);
    }

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        return segments.stream().noneMatch(this::isArrivalBeforeDeparture);
    }

}
