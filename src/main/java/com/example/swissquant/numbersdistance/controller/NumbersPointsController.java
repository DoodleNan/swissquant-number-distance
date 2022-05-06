package com.example.swissquant.numbersdistance.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.PathParam;

import com.example.swissquant.numbersdistance.model.Point;
import com.example.swissquant.numbersdistance.service.NumbersDistanceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class NumbersPointsController {

	@Inject
	private NumbersDistanceService numbersDistanceService;
	
	/**
	 * REST POST method to return closest points from the given origin
	 *  
	 * @param origin the point from which the closest points are calculated
	 * @param numOfPoints Size of return result
	 *
	 * @return List of Points with size numOfPoints
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/closestPointsTo")
	public List<Point> findClosestPointsTo(@RequestBody Point origin, @PathParam("numOfPoints") int numOfPoints) {
		long start = System.currentTimeMillis();
		List<Point> resultList = numbersDistanceService.findClosestPointsTo(origin, numOfPoints);

		long duration = System.currentTimeMillis() - start;
		log.info("Finding {} closest point to {} in {} s", numOfPoints, origin, duration);

		return resultList;

	}
		
	/**
	 * REST POST method to return furthest points from the given origin
	 * 
	 * @param origin the point from which the furthest points are calculated
	 * @param numOfPoints Size of return result
	 *
	 * @return List of Points with size numOfPoints
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/furthestPointsFrom")
	public List<Point> findFurthestPointsFrom(@RequestBody Point origin, @PathParam("numOfPoints") int numOfPoints) {
		long start = System.currentTimeMillis();
		List<Point> resultList = numbersDistanceService.findFurthestPointsFrom(origin, numOfPoints);
		long duration = System.currentTimeMillis() - start;
		log.info("Finding {} furthest point to {} in {} s \n", numOfPoints, origin, duration);

		return resultList;
	}

}
