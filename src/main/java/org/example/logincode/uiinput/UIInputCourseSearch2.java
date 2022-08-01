package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;

import java.util.Set;

public class UIInputCourseSearch2 extends UIInput2 {

    private final ControllerCourseSearcher2 ccs2;

    public UIInputCourseSearch2(PresenterPrinter prt, ControllerCourseSearcher2 ccs2,
                                UICommandList cmdl) {
        super(prt, cmdl);
        this.ccs2 = ccs2;
    }

    @Override
    public void inputParser(String input) {
        // Unfortunately, no DI.
        InputParserClass ipc = new InputParserClass(input);
        switch (ipc.getKeyword()) {
            case "search" -> {
                Set<String> cc = ccs2.searchCurrentCourses(ipc.getArg(1));
                getPrt().printIterable(cc);
            }
            case "searchmeetings" -> {
                String arg1 = ipc.getArg(1);
                Set<String> toPrint = ccs2.searchMeetings(arg1);
                getPrt().printIterable(toPrint);
            }
            default -> getPrt().failInvalidCommand();
        }
    }

    @Override
    public void printCommandList() {
        this.cmdList.printSearch();
    }
}
