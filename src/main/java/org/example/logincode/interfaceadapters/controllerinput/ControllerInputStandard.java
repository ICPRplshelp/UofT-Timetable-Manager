package org.example.logincode.interfaceadapters.controllerinput;

import org.example.logincode.interfaceadapters.presenters.StandardPresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputStandard extends ControllerInput {

    protected final StandardPresenter presenter;

    public ControllerInputStandard(AccountManager manager, StorageManager accountStorageManager, StandardPresenter presenter) {
        super(manager, accountStorageManager, presenter);
        this.presenter = presenter;
        curState = LoggedInState.STANDARD;
        commandsList = new String[]{"history", "adminview", "coursesearch", "ttview", "setpassword", "secretadmin"};
    }

    @Override
    public boolean inputParser(String input) {
        switch (input) {
            case "history" -> printUserHistory();
            case "adminview" -> switchToAdminView();
            case "coursesearch" -> this.curState = LoggedInState.COURSE_SEARCHER;
            case "ttview" -> this.curState = LoggedInState.TIMETABLE;

            case "setpassword" -> changePassword();
            case "secretadmin" -> manager.makeMeAnAdmin();
            default -> {
                return failedAction();
            }
        }
        // it is only reached if the default isn't called.
        return true;
    }

    private void changePassword() {
        String[] newPasswords = presenter.passwordChangePrompt();
        boolean passwordSuccess = manager.setPassword(newPasswords[0], newPasswords[1]);
        if (!passwordSuccess) {
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
