package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;

/**
 * Filters flights with at least one impossible transfer (left segment's arrival is after right segment's departure).
 */
public class IncorrectSegmentOrderFlightFilter implements FlightFilter, TransferManaging {

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (int i = 1; i < segments.size(); i++) {
            Segment previousSegment = segments.get(i - 1);
            Segment nextSegment = segments.get(i);
            long transferTime = getSingleTransferDurationInMillis(previousSegment, nextSegment);
            if (transferTime < 0) {
                return false;
            }
        }
        return true;
    }

}
