package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.filter.FlightFilter;

import java.util.concurrent.TimeUnit;

/**
 * Bound up with time limit filters.
 */
public abstract class TimeFlightFilter implements FlightFilter, TransferManaging {

    protected final long timeLimit;

    public TimeFlightFilter(int timeLimit, TimeUnit timeUnit) {
        if (timeLimit < 0) {
            this.timeLimit = 0;
            return;
        }
        switch (timeUnit) {
            case DAYS:
                timeLimit *= 24;
            case HOURS:
                timeLimit *= 60;
            case MINUTES:
                timeLimit *= 60_000;
                break;
            default:
                timeLimit = 0;
        }
        this.timeLimit = timeLimit;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

}
