package org.example.logincode.interfaceadapters.controllerinput;

import org.example.logincode.interfaceadapters.presenters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public abstract class ControllerInput {

    /**
     * curState is an enum that determines which ControllerInput
     * the Controller class should display next.
     * This is checked in the Controller class after
     * inputParser is run.
     * <p>
     * The ControllerInput in all constructors that inherit
     * this abstract class must be an enum that is connected
     * to itself.
     *
     * @return the current state of the current object.
     */
    public LoggedInState getCurState() {
        return curState;
    }

    // which window should it be next?
    protected LoggedInState curState;
    protected AccountManager manager;
    protected StorageManager accountStorageManager;
    protected Presenter presenter;

    public String[] commandsList;

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param presenter             ^
     */
    public ControllerInput(AccountManager manager,
                           StorageManager accountStorageManager,
                           Presenter presenter) {
        // I made an entire python app to quickly process enums
        this.manager = manager;
        this.accountStorageManager = accountStorageManager;
        this.presenter = presenter;
    }


    /**
     * Takes an input.
     * <p>
     * Implementing class must
     * implement a method that reacts to the input.
     * <p>
     * IF input == null, THEN THIS METHOD MUST ALWAYS
     * RETURN false.
     * Note that this is not an if-and-only-if.
     * <p>
     * Classes that use this method must check curState
     * immediately afterwards to know if the window
     * has been switched or not.
     *
     * @param input the input sent by the user.
     *              THE INPUT IS NULLABLE!!!
     * @return whether the input was accepted or not. Return false
     * if and only if the input resulted in the default c ase.
     */
    public abstract boolean inputParser(String input);

    /**
     * This command is to be run if the user types a command
     * that is not supported by the current
     * controller input.
     *
     * @return false always.
     */
    protected boolean failedAction() {
        presenter.genericFailedAction("invalid");
        return false;
    }
}
