package org.example.requisitechecker.usecases;

import org.example.requisitechecker.entities.TemplateList;
import org.example.requisitechecker.usecases.internal.ExclusionListBuilder;
import org.example.requisitechecker.usecases.internal.PrerequisiteListBuilder;
import org.example.requisitechecker.usecases.internal.RequisiteListBuilder;

import java.util.Collection;

/**
 * This class is used to check prerequisites.
 */
public class RequisiteChecker {
    private final TemplateList templateList;

    /**
     * Creates this class.
     * This overload always assumes exclusionMode is false.
     *
     * @param reqString a copy-paste of any UofT prerequisite from
     *                  the ArtSci timetable.
     */
    public RequisiteChecker(String reqString) {
        this(reqString, false);
    }

    /**
     * Creates this class.
     *
     * @param reqString a copy-paste of any UofT prerequisite from
     *                  the ArtSci timetable.
     * @param exclusionMode set to true if reqString is from a list of exclusions.
     *                      otherwise, set it to false.
     */
    public RequisiteChecker(String reqString, boolean exclusionMode){
        RequisiteListBuilder requisiteListBuilder = exclusionMode ?
                new ExclusionListBuilder() : new PrerequisiteListBuilder();
        this.templateList = requisiteListBuilder.buildRequisiteList(reqString);
    }
    

    /**
     * Given a collection of courses, this method
     * checks if the collection of courses meets
     * the requirements of this TemplateList.
     * @param coursesAsString a set of courses as String literals to check.
     * @return the result
     */
    public boolean check(Collection<String> coursesAsString){
        return this.templateList.check(coursesAsString);
    }
}
