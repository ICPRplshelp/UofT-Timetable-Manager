package org.example.logincode.interfaceadapters.controllers;

import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;


public abstract class ControllerBase {

    protected final AccountManager manager;
    protected final StorageManager accountStorageManager;

    public LoggedInState getCurState() {
        return curState;
    }

    LoggedInState curState;


    public ControllerBase(AccountManager manager, StorageManager accountStorageManager) {

        this.manager = manager;
        this.accountStorageManager = accountStorageManager;

    }

    public ControllerBase(AccountManager manager, StorageManager accountStorageManager, LoggedInState curState) {
        this.curState = curState;
        this.manager = manager;
        this.accountStorageManager = accountStorageManager;

    }

    public boolean returnToStandardView() {
        this.curState = LoggedInState.STANDARD;
        return true;
    }
}
