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
                boolean addedCourseState = sm.addBlankPlannedCourse(temp);
                if(addedCourseState){
                    prt.println("Added the course.");
                } else {
                    prt.println("Couldn't add the course - it either didn't exist or was a duplicate");
                }
            }
            case "addmeetingtocourse" -> {
                String courseCode = prt.askWithMessage("What course would you add a section to?");
                if (sm.getPlannedCourseByString(courseCode) != null) {
                    String sectionCode = prt.askWithMessage("What section would you like to add?");
                    boolean setCourseState = sm.setCourseChoice(sm.getPlannedCourseByString(courseCode), sectionCode);
                    if(setCourseState) {
                        prt.println("Meeting added.");
                    } else prt.println("That course doesn't have this meeting");
                }
                return true;
            }
            case "addprevcourse" -> {
                String courseCode = prt.askWithMessage("What course would you like to add?");
                String session = prt.askWithMessage("In which session did you take this course?"); // instructions for year + 9 / 5 etc.?
                Course temp = csg.getCourseSearcher().getCourseOfferingByCode(session, courseCode);
                return sm.addPreviousCourse(temp);
            }
            case "delcourse" -> {
                String courseCode = prt.askWithMessage("What course would you like to delete?");
                return sm.removePlannedCourse(sm.getPlannedCourseByString(courseCode));
            }
            case "delprevcourse" -> {
                String courseCode = prt.askWithMessage("What previous course would you like to remove?");
                return sm.removePreviousCourse(sm.getPreviousCourseByString(courseCode));
            }
            case "donothing" -> {return true;}

        }

        return false;
    }

    public TimetableCommunicatorBulk getTCB(){
        return timetableCommunicatorBulkBuilder.buildit(manager);
    }


}
