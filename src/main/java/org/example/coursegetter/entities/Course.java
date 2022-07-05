package org.example.coursegetter.entities;

import java.util.Map;

public class Course {
    public double getCreditValue() {
        return creditValue;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getCode() {
        return code;
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
    // public final int brq;
    public final BreadthRequirement brc;

    public Course(Map<String, Object> cInfo){

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

    private double calculateCourseCreditValue(){
        if(org.equals("PDC")) return 0.0;
        else return this.code.charAt(6) == 'Y' ? 1 : 0.5;
    }

    @Override
    public String toString() {
        return this.code + "-" + this.section;
    }

    private int getLevelFromCourseCode(String cc){
        char levelChar = cc.charAt(3);
        switch (levelChar) {
            case '0':
                return 0;
            case '2':
            case 'B':
                return 2;
            case '3':
            case 'C':
                return 3;
            case '4':
            case 'D':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 1;
        }
    }
}
