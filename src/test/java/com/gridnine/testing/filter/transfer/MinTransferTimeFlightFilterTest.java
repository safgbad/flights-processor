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

class MinTransferTimeFlightFilterTest {

    private final FlightFilter out = new MinTransferTimeFlightFilter(30, TimeUnit.MINUTES);

    @Test
    public void shouldReturnFalseWhenThereIsNotEnoughTimeToTransferBetweenSegments() {
        LocalDateTime now = LocalDateTime.now();
        Segment firstSegment = new Segment(now, now.plusHours(1));
        Segment secondSegment = new Segment(now.plusHours(2), now.plusHours(3));
        Segment thirdSegment = new Segment(now.plusMinutes(195), now.plusHours(5));
        Flight flight = new Flight(List.of(firstSegment, secondSegment, thirdSegment));

        assertFalse(out.isAcceptable(flight));
    }

    @Test
    public void shouldReturnTrueWhenThereIsEnoughTimeToTransfer() {
        LocalDateTime now = LocalDateTime.now();
        Segment firstSegment = new Segment(now, now.plusHours(1));
        Segment secondSegment = new Segment(now.plusHours(2), now.plusHours(3));
        Segment thirdSegment = new Segment(now.plusHours(4), now.plusHours(5));
        Flight flight = new Flight(List.of(firstSegment, secondSegment, thirdSegment));

        assertTrue(out.isAcceptable(flight));
    }

}
