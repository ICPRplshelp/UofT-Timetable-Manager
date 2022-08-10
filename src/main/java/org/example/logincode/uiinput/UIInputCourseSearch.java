package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher;
import org.example.mainloophelpers.controllerspresenters.UICommandList;

import java.util.Set;

public class UIInputCourseSearch extends UIInput {

    private final ControllerCourseSearcher ccs2;

    public UIInputCourseSearch(PresenterPrinter prt, ControllerCourseSearcher ccs2,
                               UICommandList cmdl) {
        super(prt, cmdl);
        this.ccs2 = ccs2;
    }

    @Override
    public void inputParser(String input) {
        // Unfortunately, no DI.
        InputParserClass ipc = new InputParserClass(input);
        switch (ipc.getKeyword()) {
            case "search", "s" -> {
                Set<String> cc = ccs2.searchCurrentCourses(ipc.getArg(1));
                getPrt().printIterable(cc);
            }
            case "searchmeetings", "sm" -> {
                String arg1 = ipc.getArg(1);
                Set<String> toPrint = ccs2.searchMeetings(arg1);
                getPrt().printIterable(toPrint);
            }
            case "ssearch", "ss", "searchfilterissue" -> {
                Set<String> cc = ccs2.searchCoursesICanTake(ipc.getArg(1));
                getPrt().printIterable(cc);
            }
            default -> getPrt().failInvalidCommand();
        }
    }

    @Override
    public void printCommandList() {
        this.cmdList.printSearch();
    }
}
