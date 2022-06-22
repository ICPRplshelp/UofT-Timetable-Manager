package org.example.usecases;

import org.example.entities.courserelated.Course;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CourseReader {


    /**
     * Given a JSON, return a map mapping course codes
     * to course information.
     * @param rawJson the JSON to pass in.
     * @return the course information as a map from course code
     * to course information.
     */
    public Map<String, Course> getCourses(String rawJson){
        Map<String, Course> mapToExport = new HashMap<>();
        JSONObject jsonObject = new JSONObject(rawJson);
        // https://stackoverflow.com/a/68996237
        for (String key : jsonObject.keySet()) {
            Object val = jsonObject.get(key);
            Course crs = new Course(((JSONObject) val).toMap());
            mapToExport.put(key, crs);
        }
        return mapToExport;
    }
}
