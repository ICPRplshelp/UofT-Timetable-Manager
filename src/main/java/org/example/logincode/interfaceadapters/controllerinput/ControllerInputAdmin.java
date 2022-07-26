package org.example.logincode.interfaceadapters.controllerinput;

import org.example.logincode.interfaceadapters.controllers.ControllerAdmin;
import org.example.logincode.interfaceadapters.controllers.ControllerBase;
import org.example.logincode.interfaceadapters.presenters.AdminPresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.AdminAccountManager;
import org.example.logincode.usecases.StorageManager;

import java.text.ParseException;
import java.util.Date;

public class ControllerInputAdmin extends ControllerInput {

    protected final AdminPresenter presenter;
    protected final ControllerAdmin controller;
    @Override
    public ControllerBase getController() {
        return controller;
    }

    /**
     * The constructor for this class.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param presenter             ^
     */
    public ControllerInputAdmin(AccountManager manager, StorageManager accountStorageManager, AdminPresenter presenter) {
        super(manager, accountStorageManager, presenter);
        this.presenter = presenter;
        this.controller = new ControllerAdmin(manager, accountStorageManager);
        this.curState = LoggedInState.ADMIN;
        commandsList = new String[]{"ban", "delete", "new", "promote", "back"};
    }

    public boolean inputParser(String input) {

        switch (input) {
            case "ban" -> {
                String userToBan = presenter.enterUsername();
                // special case w/ the code block here; presenter.enterDate may throw an exception.
                Date unbanDate = new Date();
                try {
                    unbanDate = presenter.enterDate();
                } catch (ParseException e) {
                    // Unsure if this is supposed to be printed to screen; put into presenter for now.
                    presenter.parseFailure();
                }
                presenter.banUserConfirm(controller.banUser(userToBan, unbanDate), userToBan); }

            case "delete" -> {
                String userToDelete = presenter.enterUsername();
                presenter.deleteUserConfirm(controller.deleteUser(userToDelete), userToDelete);

            }

            case "new" -> {
                String[] inputs = presenter.enterCredentials();
                presenter.createNewAdminConfirm(controller.createNewAdminUser(inputs), inputs[0]);
            }

            case "promote" -> {
                String userToPromote = presenter.enterUsername();

                presenter.promoteUserConfirm(controller.promoteUserToAdmin(userToPromote), userToPromote);
            }
            case "back" -> controller.returnToStandardView();
            default -> {
                return this.failedAction();
            }
        }
        return true;
    }


}
