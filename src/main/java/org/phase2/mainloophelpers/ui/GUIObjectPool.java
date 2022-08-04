package org.phase2.mainloophelpers.ui;

import org.phase2.objectcreators.uifactories.GUIFactory;

import javax.swing.*;

/**
 * Because why not?
 * Layer: UI
 */
public class GUIObjectPool {

    private final GUIFactory uif;

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public JPanel getAdmin() {
        if (admin == null) admin = uif.getInputAdmin();
        return admin;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public JPanel getStandard() {
        if (standard == null) standard = uif.getInputStandard();
        return standard;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public JPanel getSearch() {
        if (search == null) search = uif.getInputCourseSearch();
        return search;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public JPanel getStudent() {
        if (student == null) student = uif.getInputStudent();
        return student;
    }

    private JPanel admin;
    private JPanel standard;
    private JPanel search;
    private JPanel student;

    public GUIObjectPool(GUIFactory uif) {
        this.uif = uif;
    }


}
