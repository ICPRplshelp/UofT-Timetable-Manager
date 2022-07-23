package org.example.courselocationtracker.usecases;

import org.example.courselocationtracker.entities.Building;
import org.example.courselocationtracker.entities.BuildingStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class BuildingStorageConstructor {

    /**
     * Construct the building storage. Note that the
     * file path is hardcoded, so please do not rename the
     * CSV file.
     *
     * @return a BuildingStorage, which stores all buildings.
     */
    public BuildingStorage makeAllBuildings() {
        String pathToFile = "src/UofTBuildingLocations.csv";
        String buildingCSV;
        buildingCSV = getBuildingCSV(pathToFile);
        Map<String, Building> buildingsSoFar = getBuildingMap(buildingCSV);
        return new BuildingStorage(buildingsSoFar);
    }

    private String getBuildingCSV(String pathToFile) {
        String buildingCSV;
        try {
            buildingCSV = Files.readString(Path.of(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buildingCSV;
    }

    private Map<String, Building> getBuildingMap(String buildingCSV) {
        Map<String, Building> buildingsSoFar = new HashMap<>();
        var splitString = buildingCSV.split("\n");
        boolean firstIter = false;
        for (var s : splitString) {
            if (!firstIter) {
                firstIter = true;
                continue;
            }
            var s2 = s.split(",");
            buildingsSoFar.put(s2[0], new Building(
                    Integer.parseInt(s2[1]),
                    Integer.parseInt(removeSuffix(s2[2], "\r"))));
        }
        return buildingsSoFar;
    }

    public String removeSuffix(String text, String suffix) {
        if (text.endsWith(suffix)) {
            return text.substring(0, text.length() - suffix.length());
        } else return text;
    }

}
