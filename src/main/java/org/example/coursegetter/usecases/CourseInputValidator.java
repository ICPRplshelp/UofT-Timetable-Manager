package org.example.coursegetter.usecases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseInputValidator {

    private final Pattern[] coursePatterns = {Pattern.compile("[A-Z]{3}[0-4]\\d{2}[HY][01]-[FSY]"),
            Pattern.compile("[A-Z]{3}[0-4]\\d{2}[HY][01][FSY]"),
            Pattern.compile("[A-Z]{3}[0-4]\\d{2}[HY][01] [FSY]")};

    /**
     * Given a course offering, return what needs to be
     * searched in UofT's timetable API.
     *
     * @param input a course entry in the form CSC110Y1-F.
     * @return the course entry that can be searched in the
     * UofT timetable API - CSC110Y1-F (do not include the
     * session number)
     * Null if the course is in an invalid format.
     */
    public String courseOfferingToSearchableCourse(String input) {
        for (int i = 0; i < this.coursePatterns.length; i++) {
            Matcher matcher = coursePatterns[i].matcher(input);
            if (!matcher.find()) {
                continue;
            }
            String matched = matcher.group(0);
            switch (i) {
                case 0:  // the standard
                    return matched;
                case 1:
                    return matched.substring(0, matched.length() - 1) + "-"
                            + matched.charAt(matched.length() - 1);
                case 2:
                    String[] splitString = matched.split(" ");
                    return splitString[0] + "-" + splitString[1];
                default:
                    return null;
            }
        }
        return null;
    }


}
