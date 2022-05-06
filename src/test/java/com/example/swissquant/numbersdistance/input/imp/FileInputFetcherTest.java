package com.example.swissquant.numbersdistance.input.imp;

import com.example.swissquant.numbersdistance.model.Point;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileInputFetcherTest {
    private static final String fileName = "points";
    private FileInputFetcher fileInputFetcher = new FileInputFetcher(fileName);
    private ConcurrentHashMap<Point, Long> pointsConcurrentHashMap;

    @Test
    public void testGetPointsConcurrentHashMap() {
        pointsConcurrentHashMap = fileInputFetcher.getPointsConcurrentHashMap();
        assertNotNull(pointsConcurrentHashMap);
        assertEquals(10000000, pointsConcurrentHashMap.size());
    }
}
