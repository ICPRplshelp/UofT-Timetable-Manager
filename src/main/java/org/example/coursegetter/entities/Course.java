package org.example.coursegetter.entities;

import java.util.Map;

public class Course {
    public final String orgName;
    public final String code;
    public final String webTimetableInstructions;
    public final String org;
    public final String session;
    public final String prerequisite;
    public final String exclusion;
    public final String section;
    public final String courseDescription;
    public final String breadthCategories;
    public final String deliveryInstructions;
    public final String courseTitle;
    public final String corequisite;
    public final Meetings meetings;
    public final int brq;

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
        this.brq = calculateBrq(this.breadthCategories);
    }

    /**
     * Calculates the breadth requirement of a course
     * based on its breadth requirement description.
     * @param brqDesc the breadth category description.
     * @return the BRQ number or 0 if it doesn't fullfill in any
     * breadth requirement.
     */
    private int calculateBrq(String brqDesc){
        for(int i = 1; i <= 5; i++){
            if(brqDesc.contains(Integer.toString(i))){
                return i;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.code + "-" + this.section;
    }
}
