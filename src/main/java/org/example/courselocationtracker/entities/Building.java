package org.example.courselocationtracker.entities;

import java.awt.*;
import java.awt.geom.Point2D;

public class Building {
    private final Point location;

    public Building(int x, int y){
        this.location = new Point(x, y);
    }

    /**
     * Compare the distance between this and the other.
     * The distance returned is always in meters.
     *
     * @param other the other building.
     * @return the taxicab distance between the two,
     * or 0 if the other is none
     */
    public double compareDistance(Building other){
        return compareDistanceUnits(other)* 1.1789;
    }

    /**
     * Compare the distance between this and other.
     * @param other the other building.
     * @return the taxicab distance between the two,
     * or 0 if other is none
     */
    private int compareDistanceUnits(Building other){
        if (other == null){
            return 0;
        }
        return Math.abs(this.location.x - other.location.x) +
                Math.abs(this.location.y - other.location.y);
    }
}
