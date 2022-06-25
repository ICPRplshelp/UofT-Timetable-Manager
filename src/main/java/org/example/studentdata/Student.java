package org.example.studentdata;

import java.util.List;

// TODO: This is an entity. Place it in an entity package.
public class Student {
    // Course offering Regex: [A-Z]{3}[0-4]\d{2}[H|Y][0159]-[FSY]
    // Course Regex [A-Z]{3}[0-4]\d{2}[H|Y][0159][FSY]

    // TODO: Delete all TODOs after completely finishing them

    // all Strings MUST match the regex of a course offering.
    // e.g. CSC110Y1-F
    // the courses a student plans to take
    // based on the order it was added by the student
    // on the front-end side of this app
    // TODO: Implement a way to sort these lists by the same way stated below
    // Exactly the same algorithm.
    public List<String> plannedFCourses;
    public List<String> plannedSCourses;
    public List<String> plannedYCourses;
    // Use cases may only touch these lists, so let the
    // use cases do whatever we want to them

    // the String within MUST match the regex of a course (NOT course offering)
    // e.g. CSC110Y1
    // The courses this student has taken before, in the order
    // it was added in the front-end
    // TODO: Implement a way to sort this list by the following:
    // left means sort it more to the left
    // Priority 1 (leftest): level of the course - 1 < 2 < 3 < 4
    // Priority 2: The last two digits of the course code - CSC258H1 -> 58
    // Priority 3: campus - 1 < 0 < 5 < 3 < 9 < 2 < 4 < 6 < 7 < 8, left is higher
    // Priority 4: sorted alphabetically the first 3 letters of the course code
    // TODO: Implement a way to move all courses in plannedFCourses to the one below
    public List<String> previousCourses;

    /**
     * Sort all the course lists.
     * TODO: IMPLEMENT THIS
     */
    public void sortAllCourseLists(){
        throw new RuntimeException();
    }





}
