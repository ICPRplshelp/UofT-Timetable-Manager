package org.example.coursegetter.entities;

import java.io.Serializable;
import java.util.*;

// TODO: Choose which variables to keep, and make getters for them
public class EnrollmentControl implements Serializable {
    private final String postId;
    private final String postCode;
    private final String postName;
    private final String subjectId;
    private final String subjectCode;
    private final String subjectName;
    private final String designationId;
    private final String designationCode;
    private final String designationName;
    private final String yearOfStudy;
    private final String typeOfProgramId;
    private final String typeOfProgramCode;
    private final String typeOfProgramName;
    private final String primaryOrgId;
    private final String primaryOrgCode;
    private final String primaryOrgName;
    private final String secondaryOrgId;
    private final String secondaryOrgCode;
    private final String secondaryOrgName;
    private final String assocOrgId;
    private final String assocOrgCode;
    private final String assocOrgName;
    private final String adminOrgId;
    private final String adminOrgCode;
    private final String adminOrgName;
    private final String restrictedGroup;



    public EnrollmentControl(Map<String, String> controls) {
        this.postId = controls.get("postId");
        this.postCode = controls.get("postCode");
        this.postName = controls.get("postName");
        this.subjectId = controls.get("subjectId");
        this.subjectCode = controls.get("subjectCode");
        this.subjectName = controls.get("subjectName");
        this.designationId = controls.get("designationId");
        this.designationCode = controls.get("designationCode");
        this.designationName = controls.get("designationName");
        this.yearOfStudy = controls.get("yearOfStudy");
        this.typeOfProgramId = controls.get("typeOfProgramId");
        this.typeOfProgramCode = controls.get("typeOfProgramCode");
        this.typeOfProgramName = controls.get("typeOfProgramName");
        this.primaryOrgId = controls.get("primaryOrgId");
        this.primaryOrgCode = controls.get("primaryOrgCode");
        this.primaryOrgName = controls.get("primaryOrgName");
        this.secondaryOrgId = controls.get("secondaryOrgId");
        this.secondaryOrgCode = controls.get("secondaryOrgCode");
        this.secondaryOrgName = controls.get("secondaryOrgName");
        this.assocOrgId = controls.get("assocOrgId");
        this.assocOrgCode = controls.get("assocOrgCode");
        this.assocOrgName = controls.get("assocOrgName");
        this.adminOrgId = controls.get("adminOrgId");
        this.adminOrgCode = controls.get("adminOrgCode");
        this.adminOrgName = controls.get("adminOrgName");
        this.restrictedGroup = controls.get("restrictedGroup");
    }

    /**
     * @return true if this enrollment control states a year of study of 1.
     */
    public boolean isFirstYearOnly(){
        return this.yearOfStudy.equals("1");
    }

    /**
     * Returns a list of display-friendly enrollment controls.
     */
    public List<String> toListString() {
        String[] items = {
                restrictedGroup != null ? "Group " + restrictedGroup : null,
                postName != null && postCode != null ? postName + " (" +
                postCode + ")" : null,
                subjectName,
                designationName,
                yearOfStudy != null && !yearOfStudy.equals("*") ? "Year " + yearOfStudy : null,
                typeOfProgramName,
                primaryOrgName,
                secondaryOrgName,
                assocOrgName,
                adminOrgName
        };

        List<String> itemsList = new ArrayList<>();
        for (String it : items){
            if (it != null && !it.equals("")) itemsList.add(it);
        }
        // System.out.println(itemsList);
        return itemsList;

    }

    @Override
    public String toString() {
        return String.join(" - ", this.toListString());
    }
}
