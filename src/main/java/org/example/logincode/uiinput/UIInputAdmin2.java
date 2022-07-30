package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerAdmin;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UIInputAdmin2 extends UIInput2 {

    private final ControllerAdmin controller;

    public UIInputAdmin2(PresenterPrinter prt, ControllerAdmin controller,
                         UICommandList cmdl) {
        super(prt, cmdl);
        this.controller = controller;
    }

    @Override
    public void inputParser(String input) {
        InputParserClass ipc = new InputParserClass(input);
        boolean ss = false;
        switch (ipc.getKeyword()) {
            case "ban" -> {
                String userToBan = ipc.getArg(1);
                String dateToParse = ipc.getArg(2);
                try {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    Date date = dateFormatter.parse(dateToParse);
                    ss = controller.banUser(userToBan, date);
                } catch (ParseException ignored) {
                }
            }
            case "delete" -> ss = controller.deleteUser(ipc.getArg(1));
            case "new" -> {
                String user = ipc.getArg(1);
                String pswd = ipc.getArg(2);
                ss = controller.createNewAdminUser(new String[]{user, pswd});
            }
            case "promote" -> ss = controller.promoteUserToAdmin(ipc.getArg(1));
            default -> getPrt().failInvalidCommand();
        }
        getPrt().genericSuccessOrFail(ss);
    }

    @Override
    public void printCommandList() {
        this.cmdList.printAdmin();
    }
}
