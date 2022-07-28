package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.interfaceadapters.controllers.ControllerStandard;
import org.example.logincode.interfaceadapters.gateways.StorageLoader;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerStandardBuilder implements ControllerBuilder{

    private final String username;
    private AccountManager manager;
    private final StorageManager storageManager;


    public ControllerStandardBuilder(String username, StorageManager sm) {
        this.username = username;
        this.storageManager = sm;
    }

    @Override
    public ControllerStandard getController() {
        buildManager(username);

        return new ControllerStandard(this.manager, this.storageManager);
    }

    private void buildManager(String username) {
        this.manager = new AccountManager(username, storageManager);
    }

}
