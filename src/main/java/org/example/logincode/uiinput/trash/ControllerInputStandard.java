package org.example.logincode.uiinput.trash;

import org.example.logincode.interfaceadapters.controllers.ControllerStandard;
import org.example.logincode.interfaceadapters.presenters.StandardPresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputStandard extends ControllerInput {

    protected final StandardPresenter presenter;

    @Override
    public ControllerStandard getController() {
        return controller;
    }

    protected final ControllerStandard controller;

    public ControllerInputStandard(AccountManager manager, StorageManager accountStorageManager, StandardPresenter presenter) {
        super(manager, accountStorageManager, presenter);
        this.presenter = presenter;
        curState = LoggedInState.STANDARD;
        controller = new ControllerStandard(manager, accountStorageManager);
        commandsList = new String[]{"history", "adminview", "coursesearch", "ttview", "setpassword", "secretadmin"};
    }

    @Override
    public boolean inputParser(String input) {
        throw new RuntimeException("STOP USING TRASHED CLASSES");
    }


}
