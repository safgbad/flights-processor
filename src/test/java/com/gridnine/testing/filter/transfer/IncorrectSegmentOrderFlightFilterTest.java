package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectSegmentOrderFlightFilterTest {

    private final FlightFilter out = new IncorrectSegmentOrderFlightFilter();

    @Test
    public void shouldReturnFalseWhenSegmentOrderIsIncorrect() {
        LocalDateTime now = LocalDateTime.now();
        Segment firstSegment = new Segment(now, now.plusHours(2));
        Segment secondSegment = new Segment(now.plusHours(1), now.plusHours(3));
        Flight flight = new Flight(List.of(firstSegment, secondSegment));

        assertFalse(out.isAcceptable(flight));
    }

    @Test
    public void shouldReturnTrueWhenSegmentOrderIsCorrect() {
        LocalDateTime now = LocalDateTime.now();
        Segment firstSegment = new Segment(now, now.plusHours(1));
        Segment secondSegment = new Segment(now.plusHours(2), now.plusHours(3));
        Flight flight = new Flight(List.of(firstSegment, secondSegment));

        assertTrue(out.isAcceptable(flight));
    }

}
