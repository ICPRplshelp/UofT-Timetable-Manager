package org.example.logincode.interfaceadapters.controllerinput;

import org.example.PresenterPrinter;
import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.logincode.interfaceadapters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.usecases.StudentManager;
import org.example.studentdata.usecases.StudentManagerBuilder;
import org.example.timetable.interfaceadapters.TimetableController;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorBulkBuilder;

public class ControllerInputTimetable extends ControllerInput {
    private final CourseSearcherGetter csg;
    private StudentManager sm;
    private PresenterPrinter prt = new PresenterPrinter();
    private TimetableController ttc;
    private TimetableCommunicatorBulkBuilder timetableCommunicatorBulkBuilder = new TimetableCommunicatorBulkBuilder();

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param presenter             ^
     */
    public ControllerInputTimetable(AccountManager manager, StorageManager accountStorageManager, Presenter presenter,
                                    CourseSearcherGetter csg) {
        super(manager, accountStorageManager, presenter);
        ttc = new TimetableController(getTCB());
        StudentManagerBuilder smb = new StudentManagerBuilder();
        sm = smb.buildStudentManager(manager);
        this.csg = csg;
    }

    @Override
    public boolean inputParser(String input) {

        switch(input){
            case "view" -> {
                ttc.presentTimeTable();
                return true;
            }
            case "addcourse" -> {
                String rqc = prt.askWithMessage("What course would you like to add?");
                Course temp = csg.getCourseSearcher().getCourseOfferingByCode("20229", rqc);
                CourseChoice cc = new CourseChoice(temp);
                sm.addPlannedCourse(cc);
            }
            case "addmeetingtocourse" -> {
                // TODO: ADD A LEC, TUT, PRA TO AN EXISTING COURSE

            }
            case "addprevcourse" -> {

            }
            case "delcourse" -> {

            }
            case "delprevcourse" -> {

            }
            case "donothing" -> {return true;}

        }

        return false;
    }

    public TimetableCommunicatorBulk getTCB(){
        return timetableCommunicatorBulkBuilder.buildit(manager);
    }


}
