package com.example.swissquant.numbersdistance.manager;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.example.swissquant.numbersdistance.input.InputFetcher;
import com.example.swissquant.numbersdistance.input.InputFetcherFactory;
import com.example.swissquant.numbersdistance.model.InputSource;
import com.example.swissquant.numbersdistance.model.Point;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Read Points from file and provide reduced map as input data. 
 * 
 * @author Frank Sprich
 *
 */
@Named
@Slf4j
@NoArgsConstructor
public class InputManager {

	private String fileName = "points";
	private ConcurrentHashMap<Point, Long> pointsConcurrentHashMap;

	/**
	 * Initialise pointsConcurrentHashMap when application starts.
	 * It performs as a "cache" in memory so that we don't need to read from file each time with a call.
	 * The input could be file, database, or cloud. We should support dynamic input source

	 */
	@PostConstruct
	public void init() {
		setPointsConcurrentHashMap();
	}

	public boolean setPointsConcurrentHashMap() {
		if (null == pointsConcurrentHashMap) {
			pointsConcurrentHashMap = new ConcurrentHashMap<>();
		}

		if (pointsConcurrentHashMap.isEmpty()) {
			InputFetcher inputFetcher = InputFetcherFactory.getInputFetcher(InputSource.FILE, fileName);
			pointsConcurrentHashMap = inputFetcher.getPointsConcurrentHashMap();
		}

		return pointsConcurrentHashMap.isEmpty() ? false : true;
	}
	
	/**
	 * Read data from file only once during runtime.
	 * 
	 * @return ConcurrentHashMap all data from file
	 */
	public ConcurrentHashMap<Point, Long> getPointsConcurrentHashMap() {

		return pointsConcurrentHashMap;
	}

}
