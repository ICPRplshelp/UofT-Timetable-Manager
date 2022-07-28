package org;

import org.example.PresenterPrinter;
import org.example.logincode.uiinput.InputParserClass;
import org.example.logincode.uiinput.UIInput2;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginPresenter;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;
import org.phase2.mainloophelpers.ui.UIObjectPool;
import org.phase2.objectcreators.uifactories.UIFactory;

/**
 * This class is responsible for the main loop. Now how are we going
 * to do it?
 */
public class MainLoop {
    private final PresenterPrinter prt = new PresenterPrinter();
    private final MAccountLoginValidator mAccountLoginValidator;
    private final MAccountLoginPresenter mAccountLoginPresenter;

    public MainLoop() {
        this.mAccountLoginValidator = new MAccountLoginValidator();
        this.mAccountLoginPresenter = new MAccountLoginPresenter();
    }

    public static void main(String[] args) {
        MainLoop ml = new MainLoop();
        ml.runThis();
    }

    public void runThis() {
        String username = attemptToLogin();
        this.mAccountLoginValidator.updateSave();
        UIFactory uiFactory = new UIFactory(username, mAccountLoginValidator);
        UIObjectPool uiObjectPool = new UIObjectPool(uiFactory);
        // MUIModes mode = MUIModes.STANDARD;
        UIInput2 curUIInput = uiFactory.getInputStandard();
        UICommandList uiCommandList = new UICommandList();
        uiCommandList.printGlobal();
        uiCommandList.printStandard();
        while (true) {
            String cmd = prt.ask();
            if (cmd.startsWith("switch") || cmd.startsWith("/switch")) {
                UIInput2 temp = swapUIInput(cmd, uiObjectPool, uiCommandList);
                if (temp != null) {
                    curUIInput = temp;
                }
                continue;
            }
            if (cmd.startsWith("exit")) {
                break;
            }
            this.mAccountLoginValidator.updateSave();
            curUIInput.inputParser(cmd);
        }

    }

    /**
     * Attempts to swap the UI Input.
     *
     * @param uiMode the mode to swap to
     * @param op     the UI object pool.
     * @return the UIInput if a swap was successful, null otherwise.
     */
    public UIInput2 swapUIInput(String uiMode, UIObjectPool op, UICommandList cmdList) {
        switch (new InputParserClass(uiMode).getArg(1)) {
            case "standard" -> {
                cmdList.printStandard();
                return op.getStandard();
            }
            case "admin", "adminview" -> {
                cmdList.printAdmin();
                return op.getAdmin();
            }
            case "student", "timetable" -> {
                cmdList.printStudent();
                return op.getStudent();
            }
            case "search", "coursesearch" -> {
                cmdList.printSearch();
                return op.getSearch();
            }
            default -> {
                cmdList.printFailure();
                return null;
            }
        }
    }

    /**
     * Attempts to log in the user.
     *
     * @return the username.
     */
    private String attemptToLogin() {
        mAccountLoginPresenter.getRegCommands();
        String result = null;
        while (result == null) {
            String cmd = prt.ask();
            result = loginCommand(cmd);
        }
        return result;
    }


    /**
     * This is the login command, where a user can try to log in.
     *
     * @param cmd the command a user may type.
     * @return the username if that is the username to log in
     * to escape the login/register loop; otherwise null.
     */
    public String loginCommand(String cmd) {
        InputParserClass ipc = new InputParserClass(cmd);
        switch (ipc.getKeyword()) {
            case "login" -> {
                if (validateAccountSignIn(ipc.getArg(1), ipc.getArg(2))) {
                    mAccountLoginPresenter.loginState(true);
                    return ipc.getArg(1);
                } else {
                    mAccountLoginPresenter.loginState(false);
                    return null;
                }
            }
            case "register" -> {
                boolean registerState = mAccountLoginValidator.registerUser(ipc.getArg(1), ipc.getArg(2));
                mAccountLoginPresenter.registerState(registerState);
            }
        }
        return null;

    }

    public boolean validateAccountSignIn(String username, String password) {
        return mAccountLoginValidator.validateLogin(username, password);
    }


}
