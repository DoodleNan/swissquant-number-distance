package com.example.swissquant.numbersdistance.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.example.swissquant.numbersdistance.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class InputManagerTest {
	
	private static InputManager inputManager;
	private static Map<Point, Long> pointsConcurrentHashMap;

	@BeforeAll
	public static void setup() {
		inputManager = new InputManager();
		inputManager.init();
	}

	@Test
	public void testGetPointsConcurrentHashMap() {
		assertNotNull(inputManager);
		pointsConcurrentHashMap = inputManager.getPointsConcurrentHashMap();
		assertNotNull(pointsConcurrentHashMap);
		assertEquals(10000000, pointsConcurrentHashMap.size());
	}

	@Test
	public void testSetPointsConcurrentHashMap() {
		assertNotNull(inputManager);
		assertTrue(inputManager.setPointsConcurrentHashMap());
	}

	@Test
	public void testInit() {
		assertNotNull(inputManager);
		inputManager.init();
		assertNotNull(pointsConcurrentHashMap);
		assertEquals(10000000, pointsConcurrentHashMap.size());
	}

}
