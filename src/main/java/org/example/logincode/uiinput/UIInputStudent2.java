package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;
import org.phase2.studentrelated.controllers.StudentController;

import java.util.Collection;

public class UIInputStudent2 extends UIInput2 {

    private final StudentController stc;


    public UIInputStudent2(PresenterPrinter prt, StudentController stc, UICommandList cmdl) {

        super(prt, cmdl);
        this.stc = stc;
    }

    @Override
    public void inputParser(String input) {

        InputParserClass ipc = new InputParserClass(input);
        boolean successState = false;  // ss
        switch (ipc.getKeyword().toLowerCase()) {
            case "a", "add", "addcourse" -> successState = stc.addCourse(ipc.getArg(1));
            case "ah", "addh", "addhistoricalcourse" -> successState = stc.addHistoricalCourse(ipc.getArg(1));
            case "am", "addm", "addmeeting", "addmeetingtoplannedcourse" ->
                    successState = stc.addMeetingToPlannedCourse(ipc.getArg(1), ipc.getArg(2));
            case "r", "remove", "removecourse" -> successState = stc.removePlannedCourse(ipc.getArg(1));
            case "rh", "removeh", "removehistoricalcourse" -> successState = stc.removeHistoricalCourse(ipc.getArg(1));
            case "view", "v" -> {

                Collection<String> pr = stc.getPresenter().getPlannedCourseInfo();

                Collection<String> ps = stc.getPresenter().getPassedCourseInfo();
                stc.getPresenter().plannedStr();
                getPrt().printIterable(pr);
                stc.getPresenter().passedStr();
                getPrt().printIterable(ps);
                return;
            }
            case "tt", "html", "gethtmltt" -> {
                String term = ipc.getArg(1);
                // get char at index 0 of term to determine which table to use
                getPrt().println(stc.getTable(term));
                return;

            }
            default -> getPrt().failInvalidCommand();
        }
        getPrt().genericSuccessOrFail(successState);
    }


    @Override
    public void printCommandList() {
        this.cmdList.printStudent();
    }
}
