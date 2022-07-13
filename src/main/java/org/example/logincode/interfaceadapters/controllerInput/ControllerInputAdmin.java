package org.example.logincode.interfaceadapters.controllerInput;

import org.example.logincode.interfaceadapters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.AdminAccountManager;
import org.example.logincode.usecases.StorageManager;

import java.text.ParseException;
import java.util.Date;

public class ControllerInputAdmin extends ControllerInputStandard {


    public ControllerInputAdmin(AccountManager manager, StorageManager accountStorageManager, Presenter presenter) {
        super(manager, accountStorageManager, presenter);
        this.curState = LoggedInState.ADMIN;
    }

    public boolean inputParser(PInputs input) {
        boolean superState = super.inputParser(input);

        // guard clause: stop if the super call was successful
        if (superState) {
            return true;
        }

        // if the super call was not successful
        AdminAccountManager adminAccountManager = new AdminAccountManager(manager, accountStorageManager);
        switch (input) {
            case BAN -> {
                String userToBan = presenter.enterUsername();
                Date unbanDate = new Date();
                try {
                    unbanDate = presenter.enterDate();
                } catch (ParseException e) {
                    // Unsure if this is supposed to be printed to screen; put into presenter for now.
                    // System.out.println("Failed to parse string to date");
                    presenter.parseFailure();
                }
                boolean banCheck = adminAccountManager.banUser(userToBan, unbanDate);
                presenter.banUserConfirm(banCheck, userToBan);

                // banUser(userToBan, unbanDate);
            }

            case DELETE -> {
                String userToDelete = presenter.enterUsername();
                boolean delUserSuccess = adminAccountManager.deleteUser(userToDelete);
                presenter.deleteUserConfirm(delUserSuccess, userToDelete);

                // deleteUser(userToDelete);
            }

            case NEW -> {
                String[] inputs = presenter.enterCredentials();
                boolean isCreated = adminAccountManager.createNewAdminUser(inputs[0], inputs[1]);
                presenter.createNewAdminConfirm(isCreated, inputs[0]);
            }

            case PROMOTE -> {
                String userToPromote = presenter.enterUsername();
                presenter.promoteUserConfirm(adminAccountManager.addPermission(userToPromote, "admin"), userToPromote);
                // promoteUserToAdmin(userToPromote);
            }
            case BACK -> curState = LoggedInState.STANDARD;default -> {
                presenter.genericFailedAction("invalid");
                return false;
            }
        }
        return true;
    }
}
