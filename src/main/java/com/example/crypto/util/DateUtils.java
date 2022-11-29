package com.example.crypto.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtils {

    public static ZonedDateTime toZonedDateTime(Long epochMilliSeconds) {
        return toZonedDateTime(Instant.ofEpochMilli(epochMilliSeconds));
    }

    private static ZonedDateTime toZonedDateTime(Instant instant) {
        return instant != null
                ? ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
                : null;
    }

}
