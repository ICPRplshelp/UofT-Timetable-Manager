package org.phase2.controllerfactories;

import org.example.logincode.interfaceadapters.controllers.ControllerStandard;
import org.example.logincode.interfaceadapters.gateways.StorageLoader;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerStandardBuilder implements ControllerBuilder{

    private final String username;
    private AccountManager manager;
    private StorageManager storageManager;


    public ControllerStandardBuilder(String username) {
        this.username = username;
    }

    @Override
    public ControllerStandard getController() {
        buildAccountStorageManager();
        buildManager(username);

        return new ControllerStandard(this.manager, this.storageManager);
    }

    private void buildManager(String username) {
        this.manager = new AccountManager(username, storageManager);
    }

    private void buildAccountStorageManager() {
        this.storageManager = new StorageManager(new StorageLoader());
    }
}
