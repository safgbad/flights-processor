package com.gridnine.testing.filter;

import java.time.LocalDateTime;

public class PastDepartingFlightFilter {

    private final LocalDateTime currentDateTime;

    public PastDepartingFlightFilter() {
        this.currentDateTime = LocalDateTime.now();
    }

}
