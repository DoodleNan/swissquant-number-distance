package com.example.swissquant.numbersdistance.calculator.imp;

import com.example.swissquant.numbersdistance.calculator.ClosestPointsToCalculator;
import com.example.swissquant.numbersdistance.model.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Brute force closet number distance calculator.
 */
public class BruteForceClosestPointsToCalculator implements ClosestPointsToCalculator {
    /**
     * Calculate the points set that closet distance to the given origin
     * @param origin Point to which the distance is calculated
     * @param numOfPoints num of Points returned for a given query
     * @param pointsConcurrentHashMap  Points map which is loaded at application starts serving as in memory cache for performance
     *
     * @return List of Points which are closet to the given origin with size numOfPoints.

     */
    public List<Point> calculateClosestPointsTo(Point origin, int numOfPoints, ConcurrentHashMap<Point, Long> pointsConcurrentHashMap) {
        PriorityQueue<Point> pq = new PriorityQueue<>(numOfPoints, new Comparator<Point>() {
            // Max heap with fixed size numOfPoints. Keeps numOfPoints min distances points
            // Sort by distance to given origin, then x coordinate then y.
            @Override
            public int compare(Point point1, Point point2) {
                int ret = Double.compare(point2.distanceTo(origin), point1.distanceTo(origin));
                if(ret == 0) {
                    ret = Double.compare(point1.getX(), point2.getX());
                    if(ret == 0) {
                        ret = Double.compare(point1.getY(), point2.getY());
                    }
                }

                return ret;
            }
        });

        for(Map.Entry<Point, Long> entry : pointsConcurrentHashMap.entrySet()) {
            Point point = entry.getKey();

            if(pq.size() < numOfPoints) {
                pq.offer(point);
            }else{
                if(pq.peek().distanceTo(origin) > point.distanceTo(origin)) {
                    pq.poll();
                    pq.offer(point);
                }
            }
        }
        return new ArrayList<>(pq);
    }
}
