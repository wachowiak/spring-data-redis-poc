package org.wachowiak.spring.cache.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TimeUtils {

    private static final Logger LOG = LoggerFactory.getLogger(TimeUtils.class);

    private TimeUtils(){}

    public static void sleep(long millis){
        try {
            LOG.debug("time consuming operation in progress...");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOG.warn("time consuming operation interrupted", e);
        }
    }
}
