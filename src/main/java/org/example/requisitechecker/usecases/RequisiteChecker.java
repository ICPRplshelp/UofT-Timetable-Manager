package org.example.requisitechecker.usecases;

import org.example.requisitechecker.entities.RequisiteList;
import org.example.requisitechecker.usecases.internal.ExclusionListBuilder;
import org.example.requisitechecker.usecases.internal.PrerequisiteListBuilder;

import java.util.Collection;

/**
 * This class is used to check prerequisites.
 */
public class RequisiteChecker {
    // private final RequisiteList requisiteList;
    PrerequisiteListBuilder plb = new PrerequisiteListBuilder();
    ExclusionListBuilder elb = new ExclusionListBuilder();


    /**
     * Given a collection of courses, this method
     * checks if the collection of courses meets
     * the requirements of this RequisiteList.
     *
     * @param coursesAsString   a set of courses as String literals to check.
     * @param requisiteAsString the requisite as the string.
     * @return the result
     */
    public boolean check(Collection<String> coursesAsString,
                         String requisiteAsString) {
        RequisiteList temp = plb.buildRequisiteList(requisiteAsString);
        return temp.check(coursesAsString);
    }

    /**
     * Given a collection of courses, this method
     * checks if the collection of courses meets
     * the requirements of this RequisiteList.
     *
     * @param coursesAsString    a set of courses as String literals to check.
     * @param exclusionsAsString the exclusions as the string.
     * @return the result
     */
    public boolean checkExclusions(Collection<String> coursesAsString,
                                   String exclusionsAsString) {
        RequisiteList temp = elb.buildRequisiteList(exclusionsAsString);
        return temp.check(coursesAsString);
    }
}
