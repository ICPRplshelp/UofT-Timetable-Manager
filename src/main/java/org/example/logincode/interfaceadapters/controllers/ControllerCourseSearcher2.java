package org.example.logincode.interfaceadapters.controllers;

import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.CourseSearchAdapterPrev;

import java.util.Set;
import java.util.stream.Collectors;

public class ControllerCourseSearcher2 {

    private final CourseSearchAdapter csa;
    private final CourseSearchAdapterPrev pcsa;

    public ControllerCourseSearcher2(CourseSearchAdapter csa,
                                     CourseSearchAdapterPrev pcsa) {

        this.csa = csa;
        this.pcsa = pcsa;
    }

    /**
     * Query a search for the current session
     * @param keyword to be used with startswith()
     * @return a set of courses (with the -F/-Y/-S suffix)
     * which follows this set builder notation:
     * x such that x in all of F/W 2022-2023 course offerings
     * if keyword startswith x
     */
    public Set<String> searchCurrentCourses(String keyword){
        return csa.getAllCourses().stream().filter(crs -> crs.startsWith(keyword.toUpperCase()))
                .collect(Collectors.toSet());
    }


    public Set<String> searchMeetings(String crs){
        return csa.getMeetings(crs);
    }

}
