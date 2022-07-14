package org.example.logincode.interfaceadapters;

import org.example.logincode.usecases.AccountCreator;
import org.example.logincode.usecases.AccountLogin;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.logincode.interfaceadapters.controllerinput.ControllerInput;
import org.example.logincode.interfaceadapters.controllerinput.ControllerInputFactory;
import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

public class Controller {

    private final StorageManager accountStorageManager = new StorageManager();
    Presenter presenter = new Presenter();
    private AccountManager manager;


    // determines which "window" to show a logged-in user (maps to one of the ControllerInputs)
    private LoggedInState loggedInState = LoggedInState.STANDARD;  // always start with the standard presenter window
    private LoginState loginState = LoginState.LOGGED_OUT;

    private final ControllerInputFactory controllerInputFactory = new ControllerInputFactory(manager, accountStorageManager, presenter);

    /**
     * The method needed to set up the enum map. This contains a lot of boilerplate code, though.
     */
    public Controller() {
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public LoggedInState getLoggedInState() {
        return this.loggedInState;
    }

    /**
     * This method represents the logged-out menu.
     * A user may only access this if they are logged out.
     *
     * @param input the attempted input to get themselves logged in.
     */
    public void loggedOutMenu(String input) {
        if (input.equals("register")) {
            String[] inputs = presenter.enterCredentials();
            register(inputs[0], inputs[1]);

        } else if (input.equals("login")) {
            String[] inputs = presenter.enterCredentials();
            login(inputs[0], inputs[1]);
        }

    }

    /**
     * Responds to the input, which is the argument, that is the
     * string passed into this method.
     * Each input has varying implications.
     *
     * @param input the input to be read.
     */
    public void inputParser(String input) {

        // if the user is logged out, the user can either opt to "register"
        // or "login"

        ControllerInput cInput = controllerInputFactory.getControllerInput(loggedInState);
        if (cInput == null) {
            presenter.genericFailedAction("invalid");
            return;  // oops, invalid
        }
        // run the input and have it perform the operation with the input
        cInput.inputParser(input);
        // update the controller input
        if (cInput.getCurState() != null) {
            // update the current loginState.
            loggedInState = cInput.getCurState();
        }
        accountStorageManager.updateSave();
        // otherwise, do not update the current loginState

    }


    private void register(String username, String password) {
        AccountCreator tempSystem = new AccountCreator(accountStorageManager);

        if (tempSystem.createAccount(username, password) != null) {
            presenter.registerConfirm(true);
        } else {
            presenter.registerConfirm(false);
            inputParser("register");
        }
        accountStorageManager.updateSave();
    }

    /**
     * Attempt to log in the user.
     * If a login is successful, loginState will
     * be updated to reflect that the user is logged in,
     * and an account manager will be set up.
     *
     * @param username obvious
     * @param password obvious
     */
    private void login(String username, String password) {
        AccountLogin tempSystem = new AccountLogin(accountStorageManager);
        boolean loginState = tempSystem.validateLogin(username, password);
        if (!loginState) {
            presenter.loginConfirm(false);
            return;  // oops, the login failed. Now what? Try again?
        }
        // by this point, the login must be successful
        manager = new AccountManager(username, accountStorageManager);
        controllerInputFactory.setManager(manager);
        this.loginState = LoginState.LOGGED_IN;
        presenter.loginConfirm(true);
        accountStorageManager.updateSave();
    }

    enum LoginState {
        LOGGED_OUT, LOGGED_IN
    }


}
