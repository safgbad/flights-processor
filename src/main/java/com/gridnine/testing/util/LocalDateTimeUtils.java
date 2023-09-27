package com.gridnine.testing.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeUtils {

    public static long getMillisFromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

}
