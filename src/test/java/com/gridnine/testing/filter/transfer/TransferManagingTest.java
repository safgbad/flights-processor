package com.gridnine.testing.filter.transfer;

import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferManagingTest {

    private final TransferManaging out = new MaxTransferTimeFlightFilter(1, TimeUnit.HOURS);

    @Test
    public void getSingleTransferDurationInMillisTest() {
        LocalDateTime now = LocalDateTime.now();
        Segment leftBound = new Segment(now, now.plusHours(1));
        Segment rightBound = new Segment(now.plusHours(2), now.plusHours(3));
        long diff = 60 * 60 * 1000;

        assertEquals(out.getSingleTransferDurationInMillis(leftBound, rightBound), diff);
    }

}
