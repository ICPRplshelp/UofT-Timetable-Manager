package org.example.requisitechecker.usecases.internal;

import org.example.requisitechecker.entities.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class works for prerequisites and corequisites alike.
 */
public class PrerequisiteListBuilder extends RequisiteListBuilder {

    private final CourseRegexSearcher courseRegexSearcher = new CourseRegexSearcher();
    private final BracketDealer bd = new BracketDealer();


    /**
     * Build a requisite list.
     * This may be used alike for prerequisites,
     * corequisites, and recommended preparation.
     * <p>
     * Grade minimums and custom descriptions are not checked.
     *
     * @param reqStr a course string like "(MAT135H1, MAT136H1), MAT137Y1, MAT157Y1"
     * @return the RequisiteList associated with the course string.
     */
    public RequisiteList buildRequisiteList(String reqStr) {
        if (reqStr == null) return new FreePass();
        reqStr = bd.narrowAndOrSymbols(reqStr);
        return buildReqListHelper(reqStr);
    }

    /**
     * Helper to build a requisite list.
     *
     * @param courseStr a course string like "(MAT135H1, MAT136H1), MAT137Y1, MAT157Y1"
     * @return the RequisiteList associated with the course string.
     */
    private RequisiteList buildReqListHelper(String courseStr) {
        AOR andState = AOR.AND;
        List<String> courseList = bd.quickNestlessSplit(courseStr, AOR.AND);
        if (courseList.size() == 1) {
            courseList = bd.quickNestlessSplit(courseStr, AOR.OR);
            andState = AOR.OR;
        }  // the above checks if no ANDs in courseStr; below checks if it's a single course
        if (courseList.size() == 1) return narrowDownCourse(courseList);
        if (courseList.size() == 0) return new FreePass();  // no course -> free pass
        Set<RequisiteList> courseSetSoFar = new HashSet<>();
        courseList.forEach(crs -> createAndAddTemplateList(crs.trim(), courseSetSoFar));
        return andState == AOR.AND ? new AllList(courseSetSoFar) : new AnyList(courseSetSoFar);
    }

    /**
     * Given an existing processed course string and template list,
     * append it to the template list if crs is a valid course string.
     *
     * @param crs             the course string
     * @param coursesSetSoFar the template list to add on
     */
    private void createAndAddTemplateList(String crs, Set<RequisiteList> coursesSetSoFar) {
        RequisiteList ctl = buildReqListHelper(bd.removeHuggingBrackets(crs));
        if (!(ctl.alwaysTrue())) coursesSetSoFar.add(ctl);
    }

    /**
     * Only accepting inputs where courseList is size 1 or below,
     * read the course it may contain.
     *
     * @param courseList the courseList to read.
     * @return a SingleCourse if courseList has a course, or FreePass
     * otherwise.
     * It only accounts for the first course that appears.
     */
    private RequisiteList narrowDownCourse(List<String> courseList) {
        String attempt = courseRegexSearcher.lookForCourse(courseList.get(0));
        if (attempt != null) return new SingleCourse(attempt);
        else return new FreePass();
    }
}
