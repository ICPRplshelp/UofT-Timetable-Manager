package org;

import org.example.PresenterPrinter;
import org.example.logincode.uiinput.InputParserClass;
import org.jetbrains.annotations.NotNull;
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
        ExitState prevExitState = ExitState.JUST_STARTED;
        while (prevExitState != ExitState.EXIT) {
            prevExitState = ml.runThis();
        }
    }

    public ExitState runThis() {
        String username = attemptToLogin();
        this.mAccountLoginValidator.updateSave();
        UIFactory uiFactory = new UIFactory(username, mAccountLoginValidator);
        UIObjectPool uiObjectPool = new UIObjectPool(uiFactory);
        UICommandList uiCommandList = new UICommandList();
        MContext context = new MContext(uiFactory, uiObjectPool, uiCommandList, "standard");
        String latestCommand;
        context.printGlobalCommands();
        latestCommand = beginLoop(context, uiCommandList);
        this.mAccountLoginValidator.updateSave();
        if (latestCommand.equals("logout")) {
            return ExitState.LOGOUT;
        } else return ExitState.EXIT;

    }

    /**
     * Starts the input loop.
     *
     * @param context the context to pass in.
     * @return what the user requested when exiting.
     */
    @NotNull
    private String beginLoop(MContext context, UICommandList uiCommandList) {
        String latestCommand;
        while (true) {

            context.printCommandList();
            latestCommand = removeFirstSlash(prt.ask());
            if (latestCommand.startsWith("switch") || latestCommand.startsWith("switchto") ||
                    latestCommand.startsWith("sw")) {
                boolean successState = context.setState(latestCommand.substring(Math.max(latestCommand.indexOf(" "), 0)));
                if (!successState)
                    uiCommandList.printSwitchFailure();
                continue;
            }

            if (latestCommand.equals("exit") || latestCommand.equals("logout")) {
                break;
            }
            context.input(latestCommand);
            this.mAccountLoginValidator.updateSave();
        }
        return latestCommand;
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

    /**
     * Removes the first slash from the string
     *
     * @param toCheck the string in question
     * @return check description
     */
    public String removeFirstSlash(String toCheck) {
        if (toCheck.startsWith("/")) {
            return toCheck.substring(1);
        }
        return toCheck;
    }


}
