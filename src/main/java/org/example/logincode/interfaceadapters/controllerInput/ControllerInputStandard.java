package org.example.logincode.interfaceadapters.controllerInput;

import org.example.logincode.interfaceadapters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputStandard extends ControllerInput {

    public ControllerInputStandard(AccountManager manager, StorageManager accountStorageManager, Presenter presenter) {
        super(manager, accountStorageManager, presenter);
        curState = LoggedInState.STANDARD;
    }

    @Override
    public boolean inputParser(String input) {
        switch (input) {
            case "history" -> {
                String ts = manager.getAccountHistoryAsString();
                presenter.printHistory(ts);
            }
            case "adminview" -> {
                if (!manager.validatePermission("admin")) {
                    presenter.genericFailedAction("noPerms");
                } else {
                    curState = LoggedInState.ADMIN;
                }
            }
            case "secretadmin" -> manager.makeMeAnAdmin();
            case "setpassword" -> {
                String[] newPswds = presenter.passwordChangePrompt();
                boolean pswdSuccess = manager.setPassword(newPswds[0], newPswds[1]);
                if (!pswdSuccess) {
                    presenter.genericFailedAction("invPassword");
                }
            }
            default -> {
                return false;
            }
        }
        // it is only reached if the default isn't called.
        return true;
    }

}
