package org.example.logincode.interfaceadapters.controllers;

import org.example.logincode.uiinput.trash.LoggedInState;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerStandard extends ControllerBase {

    public ControllerStandard(AccountManager manager, StorageManager accountStorageManager) {
        super(manager, accountStorageManager);
        this.curState = LoggedInState.STANDARD;
    }

    public boolean changePassword(String[] newPasswords) {
        return manager.setPassword(newPasswords[0], newPasswords[1]);
    }

    public boolean switchToAdminView() {
        if (!manager.validatePermission("admin")) {
            return false;
        } else {
            curState = LoggedInState.ADMIN;
            return true;
        }
    }

    public String printUserHistory() {
        return manager.getAccountHistoryAsString();
    }

    public boolean switchToCourseSearchView() {
        curState = LoggedInState.COURSE_SEARCHER;
        return true;
    }

    public boolean switchToTimetableView() {
        curState = LoggedInState.TIMETABLE;
        return true;
    }
}
