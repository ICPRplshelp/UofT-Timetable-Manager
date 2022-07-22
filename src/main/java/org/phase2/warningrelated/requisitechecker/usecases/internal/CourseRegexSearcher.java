package org.phase2.warningrelated.requisitechecker.usecases.internal;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseRegexSearcher {

    // a course pattern matching any undergraduate UofT courses.
    // "[A-Z]{3}[0-5]\\d{2}[H|Y][0159]|[A-Z]{3}[A-D]\\d{2}[H|Y]3"
    // "[A-Z]{3}[0-4]\\d{2}[H|Y][019]"

    // AAA100H1-F
    // AAA100H1

    private final Pattern coursePattern = Pattern.compile("[A-Z]{3}[0-5]\\d{2}[HY][0159]|[A-Z]{3}[A-D]\\d{2}[HY]3");


    /**
     * Looks for the first course in the string and returns it.
     * @param input the string to check.
     * @return the first regex match of a course, or null otherwise.
     */
    public String lookForCourse(String input){
        // System.out.println(input);
        Matcher matcher = coursePattern.matcher(input);
        if(matcher.find())
        return matcher.group(0);
        else return null;
    }

    /**
     * Looks for all courses in the string and returns it.
     * @param input the string to check.
     * @return a set of all courses.
     */
    public Set<String> lookForAllCourses(String input){
        HashSet<String> hs = new HashSet<>();
        Matcher matcher = coursePattern.matcher(input);
        while(matcher.find()){
            hs.add(matcher.group());
        }
        return hs;
    }

}
