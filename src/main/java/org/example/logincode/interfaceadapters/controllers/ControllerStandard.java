package org.example.logincode.interfaceadapters.controllers;

import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerStandard {

    private final AccountManager manager;

    public ControllerStandard(AccountManager manager, StorageManager accountStorageManager) {
        this.manager = manager;
        // super(manager, accountStorageManager);
        // this.curState = LoggedInState.STANDARD;
    }

    public boolean changePassword(String[] newPasswords) {
        return manager.setPassword(newPasswords[0], newPasswords[1]);
    }

    public String printUserHistory() {
        return manager.getAccountHistoryAsString();
    }

}
