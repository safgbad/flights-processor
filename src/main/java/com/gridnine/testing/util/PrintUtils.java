package com.gridnine.testing.util;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PrintUtils {

    public static void printFlights(List<Flight> flights) {
        String out = flights.stream().map(Objects::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(out);
    }

}
