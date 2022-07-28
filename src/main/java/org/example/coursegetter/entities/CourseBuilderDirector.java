package org.example.coursegetter.entities;

import org.example.coursegetter.entities.baseclasses.BreadthRequirement;
import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meetings;

import java.util.Map;

public class CourseBuilderDirector {
    private final CourseBuilder cb;

    public CourseBuilderDirector(CourseBuilder cb){
        this.cb = cb;
    }

    public Course construct(Map<String, Object> cInfo){
        cb.reset();

        String code = (String) cInfo.get("code");
        String org = (String) cInfo.get("org");
        String prerequisite = (String) cInfo.get("prerequisite");
        String exclusion = (String) cInfo.get("exclusion");
        String section = (String) cInfo.get("section");
        String courseDescription = (String) cInfo.get("courseDescription");
        String breadthCategories = (String) cInfo.get("breadthCategories");
        String deliveryInstructions = (String) cInfo.get("deliveryInstructions");
        String courseTitle = (String) cInfo.get("courseTitle");
        String corequisite = (String) cInfo.get("corequisite");
        Meetings meetings = new Meetings((Map<String, Object>) cInfo.get("meetings"), code + "-" + section);
        double creditValue = calculateCourseCreditValue(org, code);
        BreadthRequirement brc = new BreadthRequirement(breadthCategories, creditValue);

        cb.setCode(code);
        cb.setOrg(org);
        cb.setPrerequisite(prerequisite);
        cb.setExclusion(exclusion);
        cb.setSection(section);
        cb.setCourseDescription(courseDescription);
        cb.setBreadthCategories(breadthCategories);
        cb.setDeliveryInstructions(deliveryInstructions);
        cb.setCourseTitle(courseTitle);
        cb.setCorequisite(corequisite);
        cb.setMeetings(meetings);
        cb.setCreditValue(creditValue);
        cb.setBrc(brc);

        return cb.getResult();
    }

    private double calculateCourseCreditValue(String org, String code) {
        if (org.equals("PDC")) return 0.0;
        else return code.charAt(6) == 'Y' ? 1 : 0.5;
    }
}
