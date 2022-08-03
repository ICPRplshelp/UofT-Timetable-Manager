package org.example.logincode.controllerspresentersgateways.controllers;

import org.phase2.studentrelated.usecases.CourseSearcher;
import org.phase2.studentrelated.usecases.CourseSearcherPrev;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ControllerCourseSearcher2 {

    private final CourseSearcher csa;
    private final CourseSearcherPrev pcsa;
    private final WarningChecker2 wc;
    public ControllerCourseSearcher2(CourseSearcher csa,
                                     CourseSearcherPrev pcsa,
                                     WarningChecker2 wc) {
        this.wc = wc;
        this.csa = csa;
        this.pcsa = pcsa;
    }

    /**
     * Query a search for the current session
     *
     * @param keyword to be used with startswith()
     * @return a set of courses (with the -F/-Y/-S suffix)
     * which follows this set builder notation:
     * x such that x in all of F/W 2022-2023 course offerings
     * if keyword startswith x.
     * Always sorted
     */
    public Set<String> searchCurrentCourses(String keyword) {
        return csa.getAllCourses().stream().filter(crs -> crs.startsWith(keyword.toUpperCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Queries a course that won't result in any issues should I add them.
     * If you're already getting an issue, this will just return an empty set.
     *
     * @param keyword the keyword that you would normally put in when running searchCurrentCourses.
     * @return a set of searched courses; it is always sorted alphabetically.
     */
    public Set<String> searchCoursesICanTake(String keyword){
        Set<String> searched = searchCurrentCourses(keyword);
        return searched.stream().filter(crs -> !wc.checkCourseWarnings(crs).containsKey(crs))
                .collect(Collectors.toCollection(TreeSet::new));
    }




    public Set<String> searchMeetings(String crs) {
        return csa.getMeetings(crs);
    }

}
