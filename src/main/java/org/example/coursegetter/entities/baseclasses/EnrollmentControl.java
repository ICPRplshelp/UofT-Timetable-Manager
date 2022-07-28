package org.example.coursegetter.entities.baseclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnrollmentControl implements Serializable {
    private final String postCode;
    private final String postName;
    private final String subjectName;
    private final String designationName;
    private final String yearOfStudy;
    private final String typeOfProgramName;
    private final String primaryOrgName;
    private final String secondaryOrgName;
    private final String assocOrgName;
    private final String adminOrgName;
    private final String restrictedGroup;


    public EnrollmentControl(Map<String, String> controls) {
        this.postCode = controls.get("postCode");
        this.postName = controls.get("postName");
        this.subjectName = controls.get("subjectName");
        this.designationName = controls.get("designationName");
        this.yearOfStudy = controls.get("yearOfStudy");
        this.typeOfProgramName = controls.get("typeOfProgramName");
        this.primaryOrgName = controls.get("primaryOrgName");
        this.secondaryOrgName = controls.get("secondaryOrgName");
        this.assocOrgName = controls.get("assocOrgName");
        this.adminOrgName = controls.get("adminOrgName");
        this.restrictedGroup = controls.get("restrictedGroup");
    }

    /**
     * @return true if this enrollment control states a year of study of 1.
     */
    public boolean isFirstYearOnly() {
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
        for (String it : items) {
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
