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
	 * @param point distance to the given point/origin
     * @Return the distance from this Point to the given origin Point.
     */
    public double distanceTo(Point point) {
        double dx = point.getX() - this.getX();
        double dy = point.getY() - this.getY();
        return dx * dx + dy * dy;
    }

    @Override
    public boolean equals(Object object) {
        if(object == this) return true;
        if(!(object instanceof Point)) return false;

        Point point = (Point) object;
        return this.getX() == point.getX() && this.getY() == point.getY();
    }

}
