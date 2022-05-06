package com.example.swissquant.numbersdistance.calculator.imp;

import com.example.swissquant.numbersdistance.calculator.FurthestPointsFromCalculator;
import com.example.swissquant.numbersdistance.model.Point;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Brute force furthest number distance calculator.
 */
public class BruteForceFurthestPointsFromCalculator implements FurthestPointsFromCalculator {

    /**
     * Calculate the points set that furthest distance to the given origin
     * @param origin Point to which the distance is calculated
     * @param numOfPoints num of Points returned for a given query
     * @param pointsConcurrentHashMap  Points map which is loaded at application starts serving as in memory cache for performance
     *
     * @return List of Points which are furthest to the given origin with size numOfPoints.

     */
    public List<Point> calculateFurthestPointsFrom(@NonNull Point origin, @NonNull int numOfPoints, @NonNull ConcurrentHashMap<Point, Long> pointsConcurrentHashMap) {
        PriorityQueue<Point> pq = new PriorityQueue<>(numOfPoints, new Comparator<Point>() {
            // Min heap with fixed size numOfPoints. Keeps numOfPoints max distances points
            // Sort by distance to given origin, then x coordinate then y.
            @Override
            public int compare(Point point1, Point point2) {
                int ret = 0;
                ret = Double.compare(point1.distanceTo(origin), point2.distanceTo(origin));
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
                if(pq.peek().distanceTo(origin) < point.distanceTo(origin)) {
                    pq.poll();
                    pq.offer(point);
                }
            }
        }

        return new ArrayList<>(pq);
    }
}
