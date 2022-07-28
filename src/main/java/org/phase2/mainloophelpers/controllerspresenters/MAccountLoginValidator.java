package org.phase2.mainloophelpers.controllerspresenters;

import org.example.logincode.interfaceadapters.gateways.StorageLoader;
import org.example.logincode.usecases.AccountCreator;
import org.example.logincode.usecases.AccountLogin;
import org.example.logincode.usecases.IGateway;
import org.example.logincode.usecases.StorageManager;

import java.io.IOException;

/**
 * The purpose of this class is to check if a username-password
 * combination was successful.
 *
 * CA layer: Controller
 */
public class MAccountLoginValidator {

    public StorageManager getStorageManager() {
        return storageManager;
    }

    private final StorageManager storageManager;
    private final AccountLogin accountLogin;
    private final AccountCreator accountCreator;
    // constructs this class. I need this.

    public MAccountLoginValidator(){
        IGateway loadedStorage = new StorageLoader();
        storageManager = new StorageManager(loadedStorage);
        accountLogin = new AccountLogin(storageManager);
        accountCreator = new AccountCreator(storageManager);
    }

    /**
     * Does the username exist? And does the password match?
     */
    public boolean validateLogin(String username, String password){
        return accountLogin.validateLogin(username, password);
    }

    /**
     * Attempts to register the user.
     * Return true if successful,
     * and it could fail because the account exists already.
     */
    public boolean registerUser(String username, String password){
        return accountCreator.createAccountAndAddToStorage(username, password);
    }

    /**
     * Updates the ser file.
     */
    public void updateSave(){
        storageManager.updateSave();
    }

}
