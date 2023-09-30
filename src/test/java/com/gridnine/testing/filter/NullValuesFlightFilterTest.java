package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NullValuesFlightFilterTest {

    private final FlightFilter out = new NullValuesFlightFilter();

    @Test
    public void shouldReturnFalseWhenFlightIsNull() {
        assertFalse(out.isAcceptable(null));
    }

    @Test
    public void shouldReturnFalseWhenAnyOfSegmentsIsNull() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment = new Segment(now, now.plusHours(1));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        segments.add(null);
        Flight flight = new Flight(segments);

        assertFalse(out.isAcceptable(flight));
    }

    @Test
    public void shouldReturnTrueWhenThereAreNoNullValues() {
        LocalDateTime now = LocalDateTime.now();
        Segment segment = new Segment(now, now.plusHours(1));
        Flight flight = new Flight(List.of(segment));

        assertTrue(out.isAcceptable(flight));
    }

}
