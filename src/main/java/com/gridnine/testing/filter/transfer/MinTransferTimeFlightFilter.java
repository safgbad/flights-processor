package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Filters flights that have transfers lasting less than specified time limit.
 */
public class MinTransferTimeFlightFilter extends TimeFlightFilter {

    public MinTransferTimeFlightFilter(int timeLimit, TimeUnit timeUnit) {
        super(timeLimit, timeUnit);
    }

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (int i = 1; i < segments.size(); i++) {
            Segment previousSegment = segments.get(i - 1);
            Segment nextSegment = segments.get(i);
            if (getSingleTransferDurationInMillis(previousSegment, nextSegment) < timeLimit) {
                return false;
            }
        }
        return true;
    }

}
