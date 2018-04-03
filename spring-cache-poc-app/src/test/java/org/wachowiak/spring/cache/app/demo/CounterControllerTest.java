package org.wachowiak.spring.cache.app.demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CounterControllerTest {

    @Test
    public void incIncrementsTheCounter(){
        String key = "key";
        CounterController underTest = new CounterController();

        assertNull(underTest.get(key));

        underTest.inc(key);
        assertEquals(Long.valueOf(1), underTest.get(key));

        underTest.inc(key);
        assertEquals(Long.valueOf(2), underTest.get(key));
    }
}