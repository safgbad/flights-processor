package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class IncorrectSegmentOrderFlightFilter implements FlightFilter {

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (int i = 1; i < segments.size(); i++) {
            Segment nextSegment = segments.get(i);
            LocalDateTime nextSegmentDepartureDateTime = nextSegment.getDepartureDate();

            Segment previousSegment = segments.get(i - 1);
            LocalDateTime previousSegmentArrivalDateTime = previousSegment.getArrivalDate();

            if (nextSegmentDepartureDateTime.isBefore(previousSegmentArrivalDateTime)) {
                return false;
            }
        }
        return true;
    }

}
