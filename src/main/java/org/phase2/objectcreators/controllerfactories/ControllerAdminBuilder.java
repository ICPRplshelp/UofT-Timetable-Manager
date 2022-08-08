package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerAdmin;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerAdminBuilder {

    private final String username;
    private AccountManager manager;
    private final StorageManager storageManager;


    public ControllerAdminBuilder(String username, StorageManager sm) {
        this.username = username;
        this.storageManager = sm;
    }

    public ControllerAdmin getController() {
        buildManager(username);

        return new ControllerAdmin(this.manager, this.storageManager);
    }

    private void buildManager(String username) {
        this.manager = new AccountManager(storageManager.getAccount(username));
    }
}
