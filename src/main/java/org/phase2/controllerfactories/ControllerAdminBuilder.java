package org.phase2.controllerfactories;

import org.example.logincode.interfaceadapters.controllers.ControllerAdmin;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerAdminBuilder implements ControllerBuilder{

    private final String username;
    private AccountManager manager;
    private StorageManager storageManager;


    public ControllerAdminBuilder(String username) {
        this.username = username;
    }

    @Override
    public ControllerAdmin getController() {
        buildAccountStorageManager();
        buildManager(username);

        return new ControllerAdmin(this.manager, this.storageManager);
    }

    private void buildManager(String username) {
        this.manager = new AccountManager(username, storageManager);
    }

    private void buildAccountStorageManager() {
        this.storageManager = new StorageManager();
    }
}
