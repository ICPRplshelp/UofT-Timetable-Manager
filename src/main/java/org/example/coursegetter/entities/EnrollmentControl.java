package org.example.coursegetter.entities;

import java.util.*;

public class EnrollmentControl {
    public final String postId;
    public final String postCode;
    public final String postName;
    public final String subjectId;
    public final String subjectCode;
    public final String subjectName;
    public final String designationId;
    public final String designationCode;
    public final String designationName;
    public final String yearOfStudy;
    public final String typeOfProgramId;
    public final String typeOfProgramCode;
    public final String typeOfProgramName;
    public final String primaryOrgId;
    public final String primaryOrgCode;
    public final String primaryOrgName;
    public final String secondaryOrgId;
    public final String secondaryOrgCode;
    public final String secondaryOrgName;
    public final String assocOrgId;
    public final String assocOrgCode;
    public final String assocOrgName;
    public final String adminOrgId;
    public final String adminOrgCode;
    public final String adminOrgName;
    public final String restrictedGroup;

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
