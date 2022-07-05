package org.example.requisitechecker.usecases.internal;

import org.example.requisitechecker.entities.AnyList;
import org.example.requisitechecker.entities.FalsePass;
import org.example.requisitechecker.entities.RequisiteList;
import org.example.requisitechecker.entities.SingleCourse;

import java.util.HashSet;
import java.util.Set;

/**
 * Due to the nature of how ambiguously exclusions are listed,
 * exclusion lists must be built separately.
 */
public class ExclusionListBuilder extends RequisiteListBuilder {
    private final BracketDealer bd = new BracketDealer();
    private final CourseRegexSearcher courseRegexSearcher = new CourseRegexSearcher();

    /**
     * <p>
     * While exclusion might mean the opposite of a prerequisite,
     * the way exclusions are checked is the exact same way prerequisites
     * are checked.
     * </p><p>
     * If, given a collection of courses you've completed,
     * an exclusion RequisiteList returns true, it means you can't
     * take that course without it being marked Extra.
     * </p><p>
     * Unlike prerequisite and corequisite lists, an exclusion list
     * just looks for every instance of what looks like a UofT
     * course in the string passed in and adds it to an AnyList.
     * This means that, typically, an exclusion list would be
     * stricter than how UofT actually treats it.
     * </p>
     *
     * @param reqStr a copy-paste of any UofT prerequisite
     *                  from the ArtSci timetable.
     * @return an exclusion template list.
     * If a set of courses is tested with it, checking will
     * return true if there's a single match.
     * If there are no exclusions, the exclusion list will
     * always return false.
     */
    public RequisiteList buildRequisiteList(String reqStr) {
        if (reqStr == null) return new FalsePass();
        reqStr = bd.narrowAndOrSymbols(reqStr);
        Set<String> foundCourses = courseRegexSearcher.lookForAllCourses(reqStr);
        if (foundCourses.size() == 0) return new FalsePass();
        Set<RequisiteList> courseSet = new HashSet<>();
        foundCourses.forEach(crs -> courseSet.add(new SingleCourse(crs)));
        return new AnyList(courseSet);
    }
}
