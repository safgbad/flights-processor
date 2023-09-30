package com.gridnine.testing.filter.transfer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeFlightFilterTest {

    @Test
    public void minutesConstructorTest() {
        int minutes = 2;
        int millis = minutes * 60_000;
        TimeFlightFilter filter = new MaxTransferTimeFlightFilter(minutes, TimeUnit.MINUTES);

        assertEquals(filter.getTimeLimit(), millis);
    }

    @Test
    public void hoursConstructorTest() {
        int hours = 2;
        int millis = hours * 60 * 60_000;
        TimeFlightFilter filter = new MaxTransferTimeFlightFilter(hours, TimeUnit.HOURS);

        assertEquals(filter.getTimeLimit(), millis);
    }

    @Test
    public void daysConstructorTest() {
        int days = 2;
        int millis = days * 24 * 60 * 60_000;
        TimeFlightFilter filter = new MaxTransferTimeFlightFilter(days, TimeUnit.DAYS);

        assertEquals(filter.getTimeLimit(), millis);
    }

    @Test
    public void notAcceptableTimeUnitConstructorTest() {
        int seconds = 2;
        int millis = 0;
        TimeFlightFilter filter = new MaxTransferTimeFlightFilter(seconds, TimeUnit.SECONDS);

        assertEquals(filter.getTimeLimit(), millis);
    }

    @Test
    public void negativeTimeLimitContructorTest() {
        int minutes = -1;
        int millis = 0;
        TimeFlightFilter filter = new MaxTransferTimeFlightFilter(minutes, TimeUnit.MINUTES);

        assertEquals(filter.getTimeLimit(), millis);
    }

}
