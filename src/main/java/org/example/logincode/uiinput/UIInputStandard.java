package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;
import org.example.mainloophelpers.controllerspresenters.UICommandList;

public class UIInputStandard extends UIInput {

    private final ControllerStandard cs;
    private final PresenterPrinter prt = new PresenterPrinter();

    public UIInputStandard(PresenterPrinter prt, ControllerStandard cs,
                           UICommandList cmdl) {
        super(prt, cmdl);
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
            case "secretadmin" -> {
                cs.makeMeAnAdmin();
                ss = true;
            }
            default -> prt.failInvalidCommand();
        }
        prt.genericSuccessOrFail(ss);
    }

    @Override
    public void printCommandList() {
        this.cmdList.printStandard();
    }
}
