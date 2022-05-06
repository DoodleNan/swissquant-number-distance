package com.example.swissquant.numbersdistance.calculator;

import com.example.swissquant.numbersdistance.model.Point;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Calculate the closet points list to origin point, given points set
 */
public interface ClosestPointsToCalculator {
    List<Point> calculateClosestPointsTo(Point origin, int numOfPoints, ConcurrentHashMap<Point, Long> pointsConcurrentHashMap);
}
