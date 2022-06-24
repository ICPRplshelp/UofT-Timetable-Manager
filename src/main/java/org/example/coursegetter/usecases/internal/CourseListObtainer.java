package org.example.coursegetter.usecases.internal;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.CourseStorage;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * The course list obtainer that obtains courses from a local
 * file.
 * It would be much better if we obtained this from the UofT API,
 * but no, I'm not doing it.
 */
public class CourseListObtainer {

    /**
     * Locally extracts every course, which is saved in this repository.
     * @return a CourseStorage object holding every FW2022-2023 course.
     */
    public CourseStorage obtainAllCourses(){
        FileOpener fo = new FileOpener();
        String crsJsonAsStr = fo.readFile("src\\coursesMASTER.json");
        Map<String, Course> crses = getCourses(crsJsonAsStr);
        return new CourseStorage(crses, "20229");
    }

    // This should be moved to its own class only if we're going
    // to grab stuff from UofT's API.
    // Or not.
    // We can always use inheritance.
    /**
     * Given a JSON, return a map mapping course codes
     * to course information.
     *
     * @param rawJson the JSON to pass in.
     * @return the course information as a map from course code
     * to course information.
     */
    protected Map<String, Course> getCourses(String rawJson){
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
