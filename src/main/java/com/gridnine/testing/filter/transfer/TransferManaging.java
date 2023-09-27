package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;

import static com.gridnine.testing.util.LocalDateTimeUtils.getMillisFromLocalDateTime;

public interface TransferManaging {

    default long getSingleTransferDurationInMillis(Segment leftBound, Segment rightBound) {
        LocalDateTime rightBoundDepartureDateTime = rightBound.getDepartureDate();
        long rightBoundDepartureMillis = getMillisFromLocalDateTime(rightBoundDepartureDateTime);
        LocalDateTime leftBoundArrivalDateTime = leftBound.getArrivalDate();
        long leftBoundArrivalMillis = getMillisFromLocalDateTime(leftBoundArrivalDateTime);
        return rightBoundDepartureMillis - leftBoundArrivalMillis;
    }

}
