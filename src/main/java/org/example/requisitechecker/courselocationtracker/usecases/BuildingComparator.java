package org.example.requisitechecker.courselocationtracker.usecases;

import org.example.requisitechecker.courselocationtracker.entities.Building;
import org.example.requisitechecker.courselocationtracker.entities.BuildingStorage;

/**
 * Compare the distance of two buildings between each other.
 */
public class BuildingComparator {
    final BuildingStorage bs;

    public BuildingComparator(BuildingStorage bs) {
        this.bs = bs;
    }


    /**
     * Return the distance between the two potential building
     * codes.
     * <p>
     * TTC subway codes: stgeorge1, stgeorge2, museum, queenspark
     *
     * @param b1Str first building code. Ex: OI G612
     * @param b2Str second building code. Ex: MY 150
     * @return the estimated walking distance between the
     * two buildings, in meters.
     * If either building does not exist, can't be found,
     * or is marked as TBA, return 0.
     */
    public double getDistance(String b1Str, String b2Str) {
        if (b1Str == null || b2Str == null) return 0;
        b1Str = b1Str.substring(0, 2).toLowerCase();
        b2Str = b2Str.substring(0, 2).toLowerCase();
        Building b1 = this.bs.getBuilding(b1Str);
        Building b2 = this.bs.getBuilding(b2Str);
        return getDistanceBuilding(b1, b2);
    }

    private double getDistanceBuilding(Building b1, Building b2) {
        if (b1 == null || b2 == null) return 0;
        return b1.compareDistance(b2);
    }

}
