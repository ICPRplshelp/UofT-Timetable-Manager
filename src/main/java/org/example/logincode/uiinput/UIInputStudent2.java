package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.phase2.studentrelated.controllers.StudentController;

public class UIInputStudent2 extends UIInput2 {

    private final StudentController stc;

    public UIInputStudent2(PresenterPrinter prt, StudentController stc) {

        super(prt);
        this.stc = stc;
    }

    @Override
    public void inputParser(String input) {

        InputParserClass ipc = new InputParserClass(input);
        boolean successState = false;  // ss
        switch (ipc.getKeyword()) {
            case "add", "addcourse" -> successState = stc.addCourse(ipc.getArg(1));
            case "addh", "addhistoricalcourse" -> successState = stc.addHistoricalCourse(ipc.getArg(1));
            case "addmeeting", "addmeetingtoplannedcourse" ->
                    successState = stc.addMeetingToPlannedCourse(ipc.getArg(1), ipc.getArg(2));
            case "remove", "removecourse" -> successState = stc.removePlannedCourse(ipc.getArg(1));
            case "removeh", "removehistoricalcourse" -> successState = stc.removeHistoricalCourse(ipc.getArg(1));
            default -> getPrt().failInvalidCommand();
        }
        getPrt().genericSuccessOrFail(successState);
    }
}
