package org.example.logincode.interfaceadapters.controllerInput;

import org.example.logincode.interfaceadapters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.HashMap;

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
    private final HashMap<String, PInputs> enumMap = new HashMap<>();


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
        enumMap.put("register", PInputs.REGISTER);
        enumMap.put("login", PInputs.LOGIN);
        enumMap.put("history", PInputs.HISTORY);
        enumMap.put("adminview", PInputs.ADMIN_VIEW);
        enumMap.put("ban", PInputs.BAN);
        enumMap.put("delete", PInputs.DELETE);
        enumMap.put("new", PInputs.NEW);
        enumMap.put("promote", PInputs.PROMOTE);
        enumMap.put("back", PInputs.BACK);
        enumMap.put("secretadmin", PInputs.SECRET_ADMIN);
        enumMap.put("setpassword", PInputs.SET_PASSWORD);
        this.manager = manager;
        this.accountStorageManager = accountStorageManager;
        this.presenter = presenter;
    }

    public PInputs inputToEnum(String candidateInput) {
        return enumMap.get(candidateInput);
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
     * @return whether the input was accepted or not.
     */
    public abstract boolean inputParser(PInputs input);


    /**
     * Takes a string input, converts it into an enum,
     * and calls the PInputs version of this method.
     * <p>
     * This method may be safely executed by its inheritors,
     * and the overridden version of inputParser(PInputs ...)
     * will be run.
     * <p>
     * Classes that use this method must check curState
     * immediately afterwards to know if the window
     * has been switched or not.
     *
     * @param candidateInput the input to pass into the
     *                       PInputs version of this
     *                       method.
     * @return whether the input was accepted or not.
     */
    public final boolean inputParser(String candidateInput) {
        PInputs cand = inputToEnum(candidateInput);
        if (cand == null) {
            cand = PInputs.INVALID_INPUT;
        }
        return inputParser(cand);
    }
}
