package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Session;
import org.example.coursegetter.entities.SessionStorage;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The course list obtainer that obtains courses from a local
 * file.
 * It would be much better if we obtained this from the UofT API,
 * but no, I'm not doing it.
 */
class CourseStorageObtainer {

    /**
     * Locally extracts every course, which is saved in this repository.
     * @return a CourseStorage object holding every FW2022-2023 course.
     */
    public SessionStorage obtainAllCourses(){
        Set<String> sessions = new HashSet<>(List.of(new String[]{"20195", "20199", "20205", "20209", "20215", "20219", "20225", "20229"}));

        SessionStorage sessionStorage = new SessionStorage();
        for (String session: sessions) {
            sessionStorage.addSession(session, obtainSessionCourses(session));
        }
        return sessionStorage;
    }

    private Session obtainSessionCourses(String session){

        // URL url = getClass().getResource("coursesCSC.json");
        String pathToFile = "src/courses" + session + ".json";
        String crsJsonAsStr;
        try {
            crsJsonAsStr = Files.readString(Path.of(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // fo.readFile(pathToFile);

        Map<String, Course> courses = getCourses(crsJsonAsStr);
        return new Session(courses, session);

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
    private Map<String, Course> getCourses(String rawJson){
        Map<String, Course> mapToExport = new HashMap<>();
        JSONObject jsonObject = new JSONObject(rawJson);
        // https://stackoverflow.com/a/68996237
        for (String key : jsonObject.keySet()) {
            Object val = jsonObject.get(key);
            Course crs = new Course(((JSONObject) val).toMap());
            mapToExport.put(key.substring(0, 10), crs);
        }
        return mapToExport;
    }
}
