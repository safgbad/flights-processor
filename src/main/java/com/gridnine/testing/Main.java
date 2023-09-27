package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.PastDepartingFlightFilter;
import com.gridnine.testing.filter.SegmentArrivingBeforeDepartureFlightFilter;
import com.gridnine.testing.filter.transfer.MaxTransferTimeFlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.processor.FlightProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.gridnine.testing.util.PrintUtils.printFlights;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("All the flights:");
        printFlights(flights);

        System.out.println();
        System.out.println("Removed the flights that depart before now:");
        FlightFilter pastDepartingFlightFilter = new PastDepartingFlightFilter();
        FlightProcessor pastDepartingFilterFlightProcessor = new FlightProcessor(pastDepartingFlightFilter);
        List<Flight> pastDepartingFilteredFlights = pastDepartingFilterFlightProcessor.process(flights);
        printFlights(pastDepartingFilteredFlights);

        System.out.println();
        System.out.println("Removed the flights that have segments with department after arrival:");
        FlightFilter segmentArrivingBeforeDepartureFlightFilter = new SegmentArrivingBeforeDepartureFlightFilter();
        FlightProcessor segmentArrivingBeforeDepartureFilterFlightProcessor
                = new FlightProcessor(segmentArrivingBeforeDepartureFlightFilter);
        List<Flight> segmentArrivingBeforeDepartureFilteredFlights
                = segmentArrivingBeforeDepartureFilterFlightProcessor.process(flights);
        printFlights(segmentArrivingBeforeDepartureFilteredFlights);

        System.out.println();
        System.out.println("Removed the flights with total transfer time more than 2 hours:");
        FlightFilter maxTransferTimeFlightFilter = new MaxTransferTimeFlightFilter(2, TimeUnit.HOURS);
        FlightProcessor maxTransferTimeFilterFlightProcessor = new FlightProcessor(maxTransferTimeFlightFilter);
        List<Flight> maxTransferTimeFilteredFlights = maxTransferTimeFilterFlightProcessor.process(flights);
        printFlights(maxTransferTimeFilteredFlights);
    }

}
