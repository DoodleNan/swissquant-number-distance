package com.example.swissquant.numbersdistance.input;

import com.example.swissquant.numbersdistance.model.Point;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Fetcher Interface to get All points from input source. Example implementation is file input.
 */
public interface InputFetcher {
    ConcurrentHashMap<Point, Long> getPointsConcurrentHashMap();
}
