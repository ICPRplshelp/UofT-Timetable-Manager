package org.example.logincode.interfaceadapters.controllerinput;

import org.example.logincode.interfaceadapters.controllers.ControllerBase;
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
        controller = new ControllerStandard(manager, accountStorageManager, curState);
        commandsList = new String[]{"history", "adminview", "coursesearch", "ttview", "setpassword", "secretadmin"};
    }

    @Override
    public boolean inputParser(String input) {
        switch (input) {
            case "history" -> {
                presenter.printHistory(controller.printUserHistory());
            }
            case "adminview" -> {
                if (!controller.switchToAdminView())
                    presenter.genericFailedAction("noPerms");
            }
            case "coursesearch" -> controller.switchToCourseSearchView();
            case "ttview" -> controller.switchToTimetableView();

            case "setpassword" -> {
                String[] newPasswords = presenter.passwordChangePrompt();
                if (!controller.changePassword(newPasswords))
                    presenter.genericFailedAction("invPassword");
            }
            case "secretadmin" -> manager.makeMeAnAdmin();
            default -> {
                return failedAction();
            }
        }
        // it is only reached if the default isn't called.
        return true;
    }


}
