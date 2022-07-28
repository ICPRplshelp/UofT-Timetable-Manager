package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.example.logincode.interfaceadapters.controllers.ControllerStandard;

public class UIInputStandard2 extends UIInput2 {

    private final ControllerStandard cs;
    private final PresenterPrinter prt = new PresenterPrinter();

    public UIInputStandard2(PresenterPrinter prt, ControllerStandard cs) {
        super(prt);
        this.cs = cs;
    }

    @Override
    public void inputParser(String input) {
        InputParserClass ipc = new InputParserClass(input);
        boolean ss = false;
        switch (ipc.getKeyword()) {
            case "history" -> {
                prt.println(cs.getUserHistoryAsString());
                ss = true;
            }
            case "setpassword" -> ss = cs.changePassword(new String[]{ipc.getArg(1), ipc.getArg(2)});
            default -> prt.failInvalidCommand();
        }
        prt.genericSuccessOrFail(ss);
    }
}
