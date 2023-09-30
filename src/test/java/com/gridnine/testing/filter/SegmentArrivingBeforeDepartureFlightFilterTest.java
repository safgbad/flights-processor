package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SegmentArrivingBeforeDepartureFlightFilterTest {

    private final FlightFilter out = new SegmentArrivingBeforeDepartureFlightFilter();

    @Test
    public void shouldReturnFalseWhenSegmentsDepartureIsAfterArrival() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment = new Segment(now.plusHours(1), now);
        Flight flight = new Flight(List.of(segment));

        assertFalse(out.isAcceptable(flight));
    }

    @Test
    public void shouldReturnTrueWhenSegmentsDepartureIsBeforeArrival() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment = new Segment(now, now.plusMinutes(1));
        Flight flight = new Flight(List.of(segment));

        assertTrue(out.isAcceptable(flight));
    }

}
