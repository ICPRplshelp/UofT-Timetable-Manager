package org.example.requisitechecker.usecases.internal;

import org.example.requisitechecker.entities.TemplateList;

/**
 * A TemplateList builder for requisites: Prerequisites, corequisites,
 * and exclusions.
 */
public abstract class RequisiteListBuilder {

    /**
     * Build a requisite list
     * @param reqStr a copy-paste of any UofT prerequisite from the ArtSci timetable.
     * @return the TemplateList version of the prerequisite list, which can
     * be used to verify prerequisites.
     */
    public abstract TemplateList buildRequisiteList(String reqStr);
}
