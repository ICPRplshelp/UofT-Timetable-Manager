package org.example.courselocationtracker.entities;

import java.util.Map;

public class BuildingStorage {
    private final Map<String, Building> buildings;

    public BuildingStorage(Map<String, Building> buildings) {
        this.buildings = buildings;
    }

    public Building getBuilding(String building) {
        return buildings.get(building);
    }

}
