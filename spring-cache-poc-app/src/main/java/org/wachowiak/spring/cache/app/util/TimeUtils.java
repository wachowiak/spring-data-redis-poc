package org.wachowiak.spring.cache.app.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class TimeUtils {

    private TimeUtils(){}

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }

    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
