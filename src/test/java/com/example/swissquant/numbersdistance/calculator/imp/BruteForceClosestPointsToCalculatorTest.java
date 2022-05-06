package com.example.swissquant.numbersdistance.calculator.imp;

import com.example.swissquant.numbersdistance.calculator.CalculatorFactory;
import com.example.swissquant.numbersdistance.calculator.ClosestPointsToCalculator;
import com.example.swissquant.numbersdistance.model.CalculationStrategy;
import com.example.swissquant.numbersdistance.model.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BruteForceClosestPointsToCalculatorTest {
	private Point origin = new Point(0,0);
	private static final int numOfPoints = 10;
	private static final int totlaPointsCount = 10000;
    private ConcurrentHashMap<Point, Long> pointsConcurrentHashMap = fillPointConcurrentHashMap(totlaPointsCount);
	private List<Point> closestPoints = fillClosetPointsList(numOfPoints);

	private ClosestPointsToCalculator bruteForceClosestPointsToCalculator = CalculatorFactory.getClosestPointsToCalculator(CalculationStrategy.BRUTE_FORCE);;

	@Test
	public void testGetClosestPoints() {
		List<Point> closestPointListActual = bruteForceClosestPointsToCalculator.calculateClosestPointsTo(origin, numOfPoints, pointsConcurrentHashMap);
		assertNotNull(closestPointListActual);
		assertEquals(numOfPoints, closestPointListActual.size());
		closestPointListActual.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int)(o1.distanceTo(origin) - o2.distanceTo(origin));
			}
		});

		boolean match = true;
		for (int i = 0; i < closestPoints.size(); i++) {
			if(!closestPoints.get(i).equals(closestPointListActual.get(i))) {
				match = false;
			}
		}

		assertTrue(match);
	}

    private ConcurrentHashMap<Point, Long> fillPointConcurrentHashMap(int size) {
	    ConcurrentHashMap<Point, Long> pointsConcurrentHashMap = new ConcurrentHashMap<>();
	    for(int i = 0; i < size; i++) {
            pointsConcurrentHashMap.put(new Point(i,i), System.currentTimeMillis());
        }

	    return pointsConcurrentHashMap;
    }

    private List<Point> fillClosetPointsList(int size) {
	    List<Point> closetPointsList = new ArrayList<>();
	    for(int i = 0; i < size; i++) {
	        closetPointsList.add(new Point(i,i));
        }

	    return closetPointsList;
    }

}
