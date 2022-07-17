package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.interfaceadapters.LoginPresenter;
import org.example.logincode.interfaceadapters.TimetablePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.studentdata.usecases.StudentManager;
import org.example.studentdata.usecases.StudentManagerBuilder;
import org.example.timetable.interfaceadapters.TimetableController;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorBulkBuilder;

public class ControllerInputTimetable extends ControllerInput {
    private final CourseSearcherGetter csg;
    private StudentManager sm;

    private TimetablePresenter timetablePresenter = new TimetablePresenter();
    private TimetableController ttc;
    private TimetableCommunicatorBulkBuilder timetableCommunicatorBulkBuilder = new TimetableCommunicatorBulkBuilder();

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param loginPresenter             ^
     */
    public ControllerInputTimetable(AccountManager manager, StorageManager accountStorageManager, LoginPresenter loginPresenter,
                                    CourseSearcherGetter csg) {
        super(manager, accountStorageManager, loginPresenter);
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
                String rqc = timetablePresenter.addCourse();
                Course temp = csg.getCourseSearcher().getCourseOfferingByCode("20229", rqc);
                boolean addedCourseState = sm.addBlankPlannedCourse(temp);
                if(addedCourseState){
                    timetablePresenter.addCourseConfirmation();
                } else {
                    timetablePresenter.addCourseError();
                }
            }
            case "addmeetingtocourse" -> {
                String courseCode = timetablePresenter.addMeetingToCourse();
                if (sm.getPlannedCourseByString(courseCode) != null) {
                    String sectionCode = timetablePresenter.addSectionToCourse();
                    boolean setCourseState = sm.setCourseChoice(sm.getPlannedCourseByString(courseCode), sectionCode);
                    if(setCourseState) {
                        timetablePresenter.addMeetingConfirmation();
                    } else timetablePresenter.addMeetingError();
                }
                return true;
            }
            case "addprevcourse" -> {
                String courseCode = timetablePresenter.addPrevCourse();
                String session = timetablePresenter.addPrevSessionCourse(); // instructions for year + 9 / 5 etc.?
                Course temp = csg.getCourseSearcher().getCourseOfferingByCode(session, courseCode);
                boolean setCourseState = sm.addPreviousCourse(temp);
                if (setCourseState){
                    timetablePresenter.addPrevCourseConfirmation(session);
                }else timetablePresenter.addPrevCourseError();

                return setCourseState;
            }
            case "delcourse" -> {
                String courseCode = timetablePresenter.deleteCourse();

                boolean setCourseState = sm.removePlannedCourse(sm.getPlannedCourseByString(courseCode));
                if (setCourseState){
                    timetablePresenter.deleteCourseConfirmation();
                }else timetablePresenter.deleteCourseError();

                return setCourseState;
            }
            case "delprevcourse" -> {
                String courseCode = timetablePresenter.deletePrevCourse();
                boolean setCourseState = sm.removePreviousCourse(sm.getPreviousCourseByString(courseCode));
                if (setCourseState){
                    timetablePresenter.deletePrevCourseConfirmation();
                }else timetablePresenter.deletePrevCourseError();

                return setCourseState;
            }
            case "donothing" -> {return true;}

        }

        return false;
    }

    public TimetableCommunicatorBulk getTCB(){
        return timetableCommunicatorBulkBuilder.buildit(manager);
    }


}
