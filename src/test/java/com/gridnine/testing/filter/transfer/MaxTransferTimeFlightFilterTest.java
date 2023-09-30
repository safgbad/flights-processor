package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxTransferTimeFlightFilterTest {

    private final FlightFilter out = new MaxTransferTimeFlightFilter(2, TimeUnit.HOURS);

    @Test
    public void shouldReturnFalseWhenTimeLimitIsExceeded() {
        LocalDateTime now = LocalDateTime.now();
        Segment firstSegment = new Segment(now, now.plusHours(1));
        Segment secondSegment = new Segment(now.plusHours(4), now.plusHours(5));
        Flight flight = new Flight(List.of(firstSegment, secondSegment));

        assertFalse(out.isAcceptable(flight));
    }

    @Test
    public void shouldReturnTrueWhenTimeLimitIsNotExceeded() {
        LocalDateTime now = LocalDateTime.now();
        Segment firstSegment = new Segment(now, now.plusHours(1));
        Segment secondSegment = new Segment(now.plusHours(3), now.plusHours(4));
        Flight flight = new Flight(List.of(firstSegment, secondSegment));

        assertTrue(out.isAcceptable(flight));
    }

}
