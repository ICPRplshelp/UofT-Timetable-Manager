package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerStandardBuilder {

    private final String username;
    private AccountManager manager;
    private final StorageManager storageManager;


    public ControllerStandardBuilder(String username, StorageManager sm) {
        this.username = username;
        this.storageManager = sm;
    }

    public ControllerStandard getController() {
        buildManager(username);

        return new ControllerStandard(this.manager, this.storageManager);
    }

    private void buildManager(String username) {
        this.manager = new AccountManager(storageManager.getAccount(username));
    }

}
