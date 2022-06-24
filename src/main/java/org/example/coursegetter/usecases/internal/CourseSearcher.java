package org.example.coursegetter.usecases.internal;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.CourseStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseSearcher {

    private CourseStorage courseStorage;
    private final Pattern[] coursePatterns = {Pattern.compile("[A-Z]{3}[0-4]\\d{2}[H|Y][01]-[F|S|Y]"),
            Pattern.compile("[A-Z]{3}[0-4]\\d{2}[H|Y][01][F|S|Y]"),
            Pattern.compile("[A-Z]{3}[0-4]\\d{2}[H|Y][01] [F|S|Y]")};

    public CourseSearcher(CourseStorage courseStorage) {
        this.courseStorage = courseStorage;
    }

    /**
     * Given a course code, this method searches for all course
     * offerings by this course code.
     * @param crsCode the course code such as MAT135H1
     * @return a collection of courses such as MAT135H1-F, MAT135H1-S, MAT135H1-Y
     */
    public Collection<Course> getCourseByCourseCode(String crsCode){
        ArrayList<Course> courseList = new ArrayList<>();
        String[] suffixes = {"-F", "-S", "-Y"};
        for(String suffix : suffixes){
            var courseToSearch = crsCode + suffix;
            var tempCourse = getCourseOfferingByCode(courseToSearch);
            if(tempCourse != null)
                courseList.add(tempCourse);
        }
        return courseList;
    }

    /**
     * Returns a set of all courses that can be reached from the given course
     * storage.
     *
     * @return a set of all courses that can be reached from the given course storage.
     */
    public Set<String> getAllCoursesOfferingList(){
        return courseStorage.getCourseOfferingListAsString();
    }

    /**
     * Returns a set of all courses that can be reached from the given course
     * storage.
     *
     * @return a set of all courses that can be reached from the given course storage.
     */
    public Set<String> getAllCoursesList(){
        return courseStorage.getCourseListAsString();
    }


    /**
     * Gets a course.
     *
     * @param crsCode the course code, in a format similar to CSC110Y1-F
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourseOfferingByCode(String crsCode) {
        String searchableCourse = courseToSearchableCourse(crsCode);
        if(searchableCourse == null) return null;
        return courseStorage.getCourse(searchableCourse);
    }

    /**
     * Given a course offering, return what needs to be
     * searched in UofT's timetable API.
     *
     * @param input a course entry in the form CSC110Y1-F.
     * @return the course entry that can be searched in the
     *         UofT timetable API - CSC110Y1-F-20229.
     *         Null if the course is in an invalid format.
     */
    private String courseToSearchableCourse(String input) {
        for (int i = 0; i < this.coursePatterns.length; i++) {
            Matcher matcher = coursePatterns[i].matcher(input);
            if (!matcher.find()) {
                continue;
            }
            String matched = matcher.group(0);
            switch (i) {
                case 0:  // the standard
                    return matched + "-" + courseStorage.session;
                case 1:
                    return matched.substring(0, matched.length() - 1) + "-"
                            + matched.charAt(matched.length() - 1) + "-" + courseStorage.session;
                case 2:
                    String[] splitString = matched.split(" ");
                    return splitString[0] + "-" + splitString[1] + "-" + courseStorage.session;
                default:
                    return null;
            }
        }
        return null;
    }

}
