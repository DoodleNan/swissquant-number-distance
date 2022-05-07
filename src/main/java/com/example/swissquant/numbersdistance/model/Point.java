package com.example.swissquant.numbersdistance.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Data model for point, represented by x and y coordinates.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point{

	/** Coordinate on the x-axis */
	private int x;

	/** Coordinate on the y-axis */
	private int y;

    /**
     * Calculate distance to a given origin
	 * @param point distance to the given point/origin
     * @Return the square of distance from this Point to the given origin Point. We skip calculating root to save runtime.
     */
    public double distanceTo(Point point) {
        double dx = point.getX() - this.getX();
        double dy = point.getY() - this.getY();
        return dx * dx + dy * dy;
    }

    @Override
    /**
     * Override equals method to compare two points by their x,y coordinates, not reference. Used in unit test.
     */
    public boolean equals(Object object) {
        if(object == this) return true;
        if(!(object instanceof Point)) return false;

        Point point = (Point) object;
        return this.getX() == point.getX() && this.getY() == point.getY();
    }

}
