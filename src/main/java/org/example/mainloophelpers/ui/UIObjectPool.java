package org.example.mainloophelpers.ui;

import org.example.logincode.uiinput.UIInput;
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
    public UIInput getAdmin() {
        if (admin == null) admin = uif.getInputAdmin();
        return admin;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput getStandard() {
        if (standard == null) standard = uif.getInputStandard();
        return standard;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput getSearch() {
        if (search == null) search = uif.getInputCourseSearch();
        return search;
    }

    /**
     * Gets the specified UIInput.
     * Guaranteed to not be null (I hope?)
     */
    public UIInput getStudent() {
        if (student == null) student = uif.getInputStudent();
        return student;
    }

    private UIInput admin;
    private UIInput standard;
    private UIInput search;
    private UIInput student;

    public UIObjectPool(UIFactory uif) {
        this.uif = uif;
    }


}
