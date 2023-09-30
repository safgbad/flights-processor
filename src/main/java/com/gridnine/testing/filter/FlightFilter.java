package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

@FunctionalInterface
public interface FlightFilter {

    /**
     * Checks if flight satisfies the requirements of this filter.
     * @param flight instance of {@link com.gridnine.testing.model.Flight}
     * @return <b>true</b> if flight passed filtration. Otherwise, returns <b>false</b>.
     */
    boolean isAcceptable(Flight flight);

}
