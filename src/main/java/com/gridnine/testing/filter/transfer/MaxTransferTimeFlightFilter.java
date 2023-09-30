package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Filters flights with total transfer duration more than specified time limit.
 */
public class MaxTransferTimeFlightFilter extends TimeFlightFilter {

    public MaxTransferTimeFlightFilter(int timeLimit, TimeUnit timeUnit) {
        super(timeLimit, timeUnit);
    }

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalTransferDuration = 0;
        for (int i = 1; i < segments.size(); i++) {
            Segment previousSegment = segments.get(i - 1);
            Segment nextSegment = segments.get(i);
            totalTransferDuration += getSingleTransferDurationInMillis(previousSegment, nextSegment);
            if (totalTransferDuration > timeLimit) {
                return false;
            }
        }
        return true;
    }

}
