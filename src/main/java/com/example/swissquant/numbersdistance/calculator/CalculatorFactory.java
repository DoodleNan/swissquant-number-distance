package com.example.swissquant.numbersdistance.calculator;

import com.example.swissquant.numbersdistance.calculator.imp.BruteForceClosestPointsToCalculator;
import com.example.swissquant.numbersdistance.calculator.imp.BruteForceFurthestPointsFromCalculator;
import com.example.swissquant.numbersdistance.model.CalculationStrategy;
import lombok.NoArgsConstructor;

/**
 * Calculator Factory to instantialize Calculator object based on CalculationStrategy
 */
@NoArgsConstructor
public class CalculatorFactory {
	public static ClosestPointsToCalculator getClosestPointsToCalculator(CalculationStrategy calculationStrategy) {
		switch (calculationStrategy) {
			case BRUTE_FORCE:
				return new BruteForceClosestPointsToCalculator();
			// TODO: Implement real calculator to be smart :)
			case SMARTER:
				return new BruteForceClosestPointsToCalculator();
			case MORE_SMARTER:
				return new BruteForceClosestPointsToCalculator();
			default:
				throw  new IllegalArgumentException("calculationStrategy not supported");
		}
	}

	public static FurthestPointsFromCalculator getFurthestPointsFromCalculator(CalculationStrategy calculationStrategy) {
		switch (calculationStrategy) {
			case BRUTE_FORCE:
				return new BruteForceFurthestPointsFromCalculator();
			// TODO: Implement real calculator to be smart :)
			case SMARTER:
				return new BruteForceFurthestPointsFromCalculator();
			case MORE_SMARTER:
				return new BruteForceFurthestPointsFromCalculator();
			default:
				throw  new IllegalArgumentException("calculationStrategy not supported");
		}
	}
}
