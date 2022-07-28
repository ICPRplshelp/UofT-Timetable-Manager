package org.example.coursegetter.entities;

import org.example.coursegetter.entities.baseclasses.BreadthRequirement;
import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meetings;

public class CourseBuilder {
    private Course course;

    public void reset(){
        course = new Course();
    }

    public void setCode(String code){
        course.setCode(code);
    }
    public void setOrg(String org){
        course.setOrg(org);
    }
    public void setPrerequisite(String prerequisite){
        course.setPrerequisite(prerequisite);
    }
    public void setExclusion(String exclusion){
        course.setExclusion(exclusion);
    }
    public void setSection(String section){
        course.setSection(section);
    }
    public void setCourseDescription(String courseDescription){
        course.setCourseDescription(courseDescription);
    }
    public void setBreadthCategories(String breadthCategories){
        course.setBreadthCategories(breadthCategories);
    }
    public void setDeliveryInstructions(String deliveryInstructions){
        course.setDeliveryInstructions(deliveryInstructions);
    }
    public void setCourseTitle(String courseTitle){
        course.setCourseTitle(courseTitle);
    }
    public void setCorequisite(String corequisite){
        course.setCorequisite(corequisite);
    }
    public void setMeetings(Meetings meetings){
        course.setMeetings(meetings);
    }
    public void setCreditValue(double creditValue){
        course.setCreditValue(creditValue);
    }
    public void setBrc(BreadthRequirement brc){
        course.setBrc(brc);
    }

    public Course getResult(){
        return course;
    }
}
