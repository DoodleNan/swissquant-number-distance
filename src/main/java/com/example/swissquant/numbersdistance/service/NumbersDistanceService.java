package com.example.swissquant.numbersdistance.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;

import com.example.swissquant.numbersdistance.calculator.CalculatorFactory;
import com.example.swissquant.numbersdistance.calculator.ClosestPointsToCalculator;
import com.example.swissquant.numbersdistance.calculator.FurthestPointsFromCalculator;
import com.example.swissquant.numbersdistance.model.CalculationStrategy;
import com.example.swissquant.numbersdistance.model.Point;
import com.example.swissquant.numbersdistance.manager.InputManager;
import org.springframework.stereotype.Service;


/**
 * Service class to finding the closest/furthest points to a given point/origin.
 *
 */
@Service
public class NumbersDistanceService {

	private InputManager inputManager;

	@Inject
	public NumbersDistanceService(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	/**
	 * Compute closest points to given Point with specified resultSetSize.
	 * 
	 * @param origin Point provided to calculate the distance to/from
	 * @param numOfPoints num of points returned from the program, it can be configured by client via api call.
	 * @return List of closest Points 
	 */
	public List<Point> findClosestPointsTo(Point origin, int numOfPoints) {
		// TODO: Do not pass hard-coded strategy and keep it dynamic. Could be a parameter from user input request?
		ClosestPointsToCalculator closestPointsToCalculator = CalculatorFactory.getClosestPointsToCalculator(CalculationStrategy.BRUTE_FORCE);
		if(inputManager.setPointsConcurrentHashMap()){
			ConcurrentHashMap<Point, Long> pointsConcurrentHashMap = inputManager.getPointsConcurrentHashMap();
			return closestPointsToCalculator.calculateClosestPointsTo(origin, numOfPoints, pointsConcurrentHashMap);
		}else{
			throw new RuntimeException("Input Points are empty!");
		}

	}
	
	/**
	 * Compute furthest points to given Point with specified resultSetSize.
	 * 
	 * @param origin Point provided to calculate the distance to/from
	 * @param numOfPoints num of points returned from the program, it can be configured by client via api call.
	 * @return List of the furthest Points
	 */
	public List<Point> findFurthestPointsFrom(Point origin, int numOfPoints) {
		// TODO: Do not pass hard-coded strategy and keep it dynamic. Could be a parameter from user input request?
		FurthestPointsFromCalculator furthestPointsFromCalculator = CalculatorFactory.getFurthestPointsFromCalculator(CalculationStrategy.BRUTE_FORCE);
		if(inputManager.setPointsConcurrentHashMap()){
			ConcurrentHashMap<Point, Long> pointsConcurrentHashMap = inputManager.getPointsConcurrentHashMap();
			return furthestPointsFromCalculator.calculateFurthestPointsFrom(origin, numOfPoints, pointsConcurrentHashMap);
		}else{
			throw new RuntimeException("Input Points are empty!");
		}
	}

}
