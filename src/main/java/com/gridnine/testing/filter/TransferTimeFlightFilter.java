package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransferTimeFlightFilter implements FlightFilter {

    private final long maxTransferTime;

    public TransferTimeFlightFilter(int time, TimeUnit timeUnit) {
        if (time < 0) {
            maxTransferTime = 0;
            return;
        }
        switch (timeUnit) {
            case DAYS:
                time *= 24;
            case HOURS:
                time *= 60;
            case MINUTES:
                time *= 60_000;
                break;
            default:
                time = 0;
        }
        maxTransferTime = time;
    }

    private long getMillisFromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    @Override
    public boolean isAcceptable(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalTransferDuration = 0;
        for (int i = 1; i < segments.size(); i++) {
            Segment nextSegment = segments.get(i);
            LocalDateTime nextSegmentDepartureDateTime = nextSegment.getDepartureDate();
            long nextSegmentDepartureMillis = getMillisFromLocalDateTime(nextSegmentDepartureDateTime);

            Segment previousSegment = segments.get(i - 1);
            LocalDateTime previousSegmentArrivalDateTime = previousSegment.getArrivalDate();
            long previousSegmentArrivalMillis = getMillisFromLocalDateTime(previousSegmentArrivalDateTime);

            totalTransferDuration += nextSegmentDepartureMillis - previousSegmentArrivalMillis;
            if (totalTransferDuration > maxTransferTime) {
                return false;
            }
        }
        return true;
    }

}
