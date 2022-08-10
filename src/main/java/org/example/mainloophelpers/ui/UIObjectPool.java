package org.example.mainloophelpers.ui;

import org.example.logincode.uiinput.UIInput2;
import org.example.objectcreators.uifactories.UIFactory;

/**
 * Because why not?
 * Layer: UI
 */
public class UIObjectPool {

    private final UIFactory uif;

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput2 getAdmin() {
        if (admin == null) admin = uif.getInputAdmin();
        return admin;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput2 getStandard() {
        if (standard == null) standard = uif.getInputStandard();
        return standard;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput2 getSearch() {
        if (search == null) search = uif.getInputCourseSearch();
        return search;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput2 getStudent() {
        if (student == null) student = uif.getInputStudent();
        return student;
    }

    private UIInput2 admin;
    private UIInput2 standard;
    private UIInput2 search;
    private UIInput2 student;

    public UIObjectPool(UIFactory uif) {
        this.uif = uif;
    }


}
