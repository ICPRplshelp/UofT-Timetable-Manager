package org.example.globalusecases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class should not be used. AT ALL. PLEASE KEEP YOUR HANDS OFF
 */
class CourseRegex {
    private final Pattern[] coursePatterns = {Pattern.compile("[A-Z]{3}[0-4]\\d{2}[HY][01]-[FSY]"),
            Pattern.compile("[A-Z]{3}[0-4]\\d{2}[HY][01][FSY]"),
            Pattern.compile("[A-Z]{3}[0-4]\\d{2}[HY][01] [FSY]")};



    /**
     * Ensures course offerings are formatted consistently
     * @param input a course offering formatted in CSC110Y1-F, CSC110Y1F, or CSC110Y1 F
     * @return always in the form CSC110Y1-F or null if input is invalid
     */
    public String formatCourseOffering(String input) {
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
                    if(splitString.length != 2) return null;
                    return splitString[0] + "-" + splitString[1];
                default:
                    return null;
            }
        }
        return null;
    }
}
