package org.example.logincode.interfaceadapters.controllerinput;

import org.example.logincode.interfaceadapters.StandardPresenter;
import org.example.logincode.interfaceadapters.TimetablePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputStandard extends ControllerInput {

    protected StandardPresenter presenter;

    public ControllerInputStandard(AccountManager manager, StorageManager accountStorageManager, StandardPresenter presenter) {
        super(manager, accountStorageManager, presenter);
        this.presenter = presenter;
        curState = LoggedInState.STANDARD;
        commandsList = new String[]{"history", "adminview", "setpassword", "secretadmin"};
    }

    @Override
    public boolean inputParser(String input) {
        switch (input) {
            case "history" -> printUserHistory();
            case "adminview" -> switchToAdminView();
            case "ttview" -> this.curState = LoggedInState.TIMETABLE;
            case "secretadmin" -> manager.makeMeAnAdmin();
            case "setpassword" -> changePassword();
            default -> {
                return failedAction();
            }
        }
        // it is only reached if the default isn't called.
        return true;
    }

    private void changePassword() {
        String[] newPswds = presenter.passwordChangePrompt();
        boolean pswdSuccess = manager.setPassword(newPswds[0], newPswds[1]);
        if (!pswdSuccess) {
            presenter.genericFailedAction("invPassword");
        }
    }

    private void switchToAdminView() {
        if (!manager.validatePermission("admin")) {
            presenter.genericFailedAction("noPerms");
        } else {
            curState = LoggedInState.ADMIN;
        }
    }

    private void printUserHistory() {
        String ts = manager.getAccountHistoryAsString();
        presenter.printHistory(ts);
    }

}
