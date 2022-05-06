package com.example.swissquant.numbersdistance.calculator.imp;

import com.example.swissquant.numbersdistance.calculator.CalculatorFactory;
import com.example.swissquant.numbersdistance.calculator.FurthestPointsFromCalculator;
import com.example.swissquant.numbersdistance.model.CalculationStrategy;
import com.example.swissquant.numbersdistance.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class BruteForceFurthestPointsFromCalculatorTest {

    private static final int numOfPoints = 10;
    private static final int totlaPointsCount = 10000;
    private ConcurrentHashMap<Point, Long> pointsConcurrentHashMap = fillPointConcurrentHashMap(totlaPointsCount);
    private List<Point> furthestPoints = fillFurthestPointsList(numOfPoints, totlaPointsCount);
    private Point origin = new Point(0,0);

    private FurthestPointsFromCalculator bruteForceFurthestPointsFromCalculator = CalculatorFactory.getFurthestPointsFromCalculator(CalculationStrategy.BRUTE_FORCE);

    @Test
    public void testGetClosestPoints() {
        List<Point> furthestPointListActual = bruteForceFurthestPointsFromCalculator.calculateFurthestPointsFrom(origin, numOfPoints, pointsConcurrentHashMap);
        assertNotNull(furthestPointListActual);
        assertEquals(numOfPoints, furthestPointListActual.size());
        furthestPointListActual.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (int)(o2.distanceTo(origin) - o1.distanceTo(origin));
            }
        });

        boolean match = true;
        for (int i = 0; i < furthestPoints.size(); i++) {
            if(!furthestPoints.get(i).equals(furthestPointListActual.get(i))) {
                match = false;
            }
        }

        assertTrue(match);
    }

    private ConcurrentHashMap<Point, Long> fillPointConcurrentHashMap(int size) {
        ConcurrentHashMap<Point, Long> pointsConcurrentHashMap = new ConcurrentHashMap<>();
        for(int i = size; i > 0; i--) {
            pointsConcurrentHashMap.put(new Point(i,i), System.currentTimeMillis());
        }

        return pointsConcurrentHashMap;
    }

    private List<Point> fillFurthestPointsList(int size, int upperLimit) {
        List<Point> closetPointsList = new ArrayList<>();
        for(int i = size; i > 0; i--) {
            closetPointsList.add(new Point(upperLimit,upperLimit));
            upperLimit--;
        }

        return closetPointsList;
    }

}
