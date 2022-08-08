package org.phase2.mainloophelpers.controllerspresenters;

import org.example.logincode.controllerspresentersgateways.gateways.StorageLoader;
import org.example.logincode.usecases.AccountCreator;
import org.example.logincode.usecases.AccountLogin;
import org.example.logincode.usecases.IGateway;
import org.example.logincode.usecases.StorageManager;

/**
 * The purpose of this class is to check if a username-password
 * combination was successful.
 * <p>
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

    public MAccountLoginValidator() {
        storageManager = StorageManager.getInstance(new StorageLoader());
        accountLogin = new AccountLogin(storageManager);
        accountCreator = new AccountCreator(storageManager);
    }

    /**
     * Does the username exist? And does the password match?
     */
    public boolean validateLogin(String username, String password) {
        return accountLogin.validateLogin(username, password);
    }

    /**
     * Attempts to register the user.
     * Return true if successful,
     * and it could fail because the account exists already.
     */
    public boolean registerUser(String username, String password) {
        return accountCreator.createAccountAndAddToStorage(username, password);
    }

    /**
     * Updates the ser file.
     */
    public void updateSave() {
        storageManager.updateSave();
    }

}
