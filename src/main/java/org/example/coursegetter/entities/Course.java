package org.example.coursegetter.entities;

import java.util.Map;

public class Course implements Comparable<Course> {
    // public final int brq;
    public final BreadthRequirement brc;
    private final double creditValue;
    private final String orgName;
    private final String code;
    private final String webTimetableInstructions;
    private final String org;
    private final String session;
    private final String prerequisite;
    private final String exclusion;
    private final String section;
    private final String courseDescription;
    private final String breadthCategories;
    private final String deliveryInstructions;
    private final String courseTitle;
    private final String corequisite;
    private final Meetings meetings;
    private final int level;

    public Course(Map<String, Object> cInfo) {

        this.orgName = (String) cInfo.get("orgName");
        this.code = (String) cInfo.get("code");

        this.webTimetableInstructions = (String) cInfo.get("webTimetableInstructions");
        this.org = (String) cInfo.get("org");
        this.session = (String) cInfo.get("session");
        this.prerequisite = (String) cInfo.get("prerequisite");
        this.exclusion = (String) cInfo.get("exclusion");
        this.section = (String) cInfo.get("section");
        this.courseDescription = (String) cInfo.get("courseDescription");
        this.breadthCategories = (String) cInfo.get("breadthCategories");
        this.deliveryInstructions = (String) cInfo.get("deliveryInstructions");
        this.courseTitle = (String) cInfo.get("courseTitle");
        this.corequisite = (String) cInfo.get("corequisite");
        this.meetings = new Meetings((Map<String, Object>) cInfo.get("meetings"));
        this.creditValue = calculateCourseCreditValue();
        this.brc = new BreadthRequirement(this.breadthCategories, this.creditValue);
        this.level = getLevelFromCourseCode(this.code);
    }

    // Course offering Regex: [A-Z]{3}[0-4]\d{2}[H|Y][0159]-[FSY]
    // Course Regex [A-Z]{3}[0-4]\d{2}[H|Y][0159][FSY]
    public double getCreditValue() {
        return creditValue;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getCode() {
        return code;
    }

    public String getOfferingCode() {
        return code + "-" + getSection();
    }

    public String getWebTimetableInstructions() {
        return webTimetableInstructions;
    }

    public String getOrg() {
        return org;
    }

    public String getSession() {
        return session;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public String getExclusion() {
        return exclusion;
    }

    public String getSection() {
        return section;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getBreadthCategories() {
        return breadthCategories;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCorequisite() {
        return corequisite;
    }

    public Meetings getMeetings() {
        return meetings;
    }

    public int getLevel() {
        return level;
    }

    public BreadthRequirement getBrc() {
        return brc;
    }

    /**
     * A course is a first year only if and only if, for all
     * lecture meetings in this course, the meeting's
     * enrollment controls says only first year students
     * can enroll in it.
     *
     * @return whether this course is first year only.
     */
    public boolean firstYearOnly() {
        for (Meeting met : this.meetings.getLectures().values()) {
            if (!(met.getEnrollmentIndicator().equals("R1") ||
                    met.getEnrollmentIndicator().equals("R2"))) return false;

            for (EnrollmentControl ec : met.getEnrollmentControls()) {
                if (!ec.isFirstYearOnly()) return false;
            }
        }
        return true;
    }

    private double calculateCourseCreditValue() {
        if (org.equals("PDC")) return 0.0;
        else return this.code.charAt(6) == 'Y' ? 1 : 0.5;
    }

    @Override
    public String toString() {
        return this.code + "-" + this.section;
    }

    private int getLevelFromCourseCode(String cc) {
        char levelChar = cc.charAt(3);
        return switch (levelChar) {
            case '0' -> 0;
            case '2', 'B' -> 2;
            case '3', 'C' -> 3;
            case '4', 'D' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            default -> 1;
        };
    }

    @Override
    public int compareTo(Course o) {
        // Priority 1: level of the course - 1 < 2 < 3 < 4
        // Priority 2: The last two digits of the course code - CSC258H1 -> 58
        // Priority 3: campus - 1 < 0 < 5 < 3 < 9 < 2 < 4 < 6 < 7 < 8, left is higher
        // Priority 4: sorted alphabetically the first 3 letters of the course code
        int x1 = compareCourseDigits(o);
        if (x1 != 0) return x1;
        int x = compareCampus(o);
        if (x != 0) return x;
        return compareCourseOrg(o);
    }

    private int compareCourseOrg(Course o) {
        String thisFaculty = this.code.substring(0, 3).toLowerCase();
        String otherFaculty = o.code.substring(0, 3).toLowerCase();
        return thisFaculty.compareTo(otherFaculty);
    }

    private int compareCourseDigits(Course o) {
        int thisCourseDigits = Integer.parseInt(this.code.substring(3, 6));
        int otherCourseDigits = Integer.parseInt(o.code.substring(3, 6));

        if (thisCourseDigits > otherCourseDigits) {
            return 1;
        } else if (thisCourseDigits < otherCourseDigits) {
            return -1;
        }
        return 0;
    }

    private int compareCampus(Course o) {
        int[] campusPriority = {1, 0, 5, 3, 6, 2, 7, 8, 9, 4};

        int thisCampusPriority = campusPriority[Integer.parseInt(String.valueOf(this.code.charAt(7)))];
        int otherCampusPriority = campusPriority[Integer.parseInt(String.valueOf(o.code.charAt(7)))];

        if (thisCampusPriority > otherCampusPriority) {
            return 1;
        } else if (thisCampusPriority < otherCampusPriority) {
            return -1;
        }
        return 0;
    }
}
