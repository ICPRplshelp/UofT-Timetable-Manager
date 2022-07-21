package org.example.timetable.interfaceadapters;

import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.WarningLevel;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorIndividual;
import org.example.timetable.usecases.TimetableManager;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TimetableController {



    private final Presenter presenter = new Presenter();
    private final TimetableCommunicatorBulk tcb;

    private TimetableManager timetableManager;

    public TimetableController(TimetableCommunicatorBulk tcb) {
        this.tcb = tcb;
    }


    public void presentTimeTable(){
        checkWarnings();
        Collection<TimetableCommunicatorIndividual> indvC = tcb.getIndividualCommunicators();
        presenter.printTimetableInformation(indvC);
        //  presenter.printAllTimetableInformation(timetableManager.getTimetable());
    }

    public void checkWarnings()  {
        List<CourseChoice> plannedCoursesList;
//        Collection<CourseChoice> plannedCourses = tcb.getTimetable().getPlannedCourses();
        plannedCoursesList = tcb.getTimetable().getPlannedCourses().stream().collect(Collectors.toList());

        List<String> coursesAsString = new ArrayList<>();

        for (int i = 0; i < plannedCoursesList.size(); i++) {
            coursesAsString.add(plannedCoursesList.get(i).getCourse().getCode());
            if (plannedCoursesList.get(i).getCourse().firstYearOnly()) {
                setWarningsHelper("FYF", plannedCoursesList.get(i));
            }

            for (int n = 0; n < plannedCoursesList.size(); n ++) {
                boolean lecExists = (plannedCoursesList.get(i).getLectureSection() != null &&
                        plannedCoursesList.get(n).getLectureSection() != null);
                if ((plannedCoursesList.get(i) != plannedCoursesList.get(n) && lecExists)) {
                    CourseChoice choice1 = plannedCoursesList.get(i);
                    CourseChoice choice2 = plannedCoursesList.get(n);
                    if (getCourseConflict(choice1, choice2)) {setWarningsHelper("CONFLICT", plannedCoursesList.get(i));}
                }
            }
        }

        for (CourseChoice courseChoice : plannedCoursesList) {
            RequisiteChecker checker = new RequisiteChecker();
            if (checker.checkExclusions(coursesAsString, courseChoice.getCourse().getExclusion())) {
                setWarningsHelper("EXC", courseChoice);
            }
            if (!checker.check(coursesAsString, courseChoice.getCourse().getCorequisite())) {
                setWarningsHelper("CRQ", courseChoice);
            }
            if (checker.checkExclusions(coursesAsString, courseChoice.getCourse().getPrerequisite())) {
                setWarningsHelper("PRQ", courseChoice);
            }
        }

    }

    private void setWarningsHelper(String Type, CourseChoice courseChoice) {
        TimetableWarning timetableWarning = new TimetableWarning() {};
        if (Objects.equals(Type, "EXC")) {
            timetableWarning.setWarningType(WarningType.EXC);
            timetableWarning.setSeverity(WarningLevel.CRITICAL);
        } else if (Objects.equals(Type, "FYF")) {
            timetableWarning.setWarningType(WarningType.FYF);
            timetableWarning.setSeverity(WarningLevel.SEVERE);
        } else if (Objects.equals(Type, "CRQ")) {
            timetableWarning.setWarningType(WarningType.CRQ);
            timetableWarning.setSeverity(WarningLevel.CRITICAL);
        } else if (Objects.equals(Type, "CONFLICT")) {
            timetableWarning.setWarningType(WarningType.CONFLICT);
            timetableWarning.setSeverity(WarningLevel.WARNING);
        } else if (Objects.equals(Type, "PRQ")) {
            timetableWarning.setWarningType(WarningType.PRQ);
            timetableWarning.setSeverity(WarningLevel.SEVERE);
        }

        tcb.getTimetable().addWarning(courseChoice, timetableWarning);


    }

    private boolean getCourseConflict(CourseChoice choice1, CourseChoice choice2) {
        Meeting lecMeet = choice1.getCourse().getMeetings().getLectures().get(choice1.getLectureSection());
        Iterator<ScheduleEntry> c1 = lecMeet.getScheduleEntries().iterator();
        ScheduleEntry current;
        while (c1.hasNext()) {
            current = c1.next();

            Meeting lecMeet2 = choice2.getCourse().getMeetings().getLectures().get(choice2.getLectureSection());
            Iterator<ScheduleEntry> c2 = lecMeet2.getScheduleEntries().iterator();

            ScheduleEntry current2;

            while(c2.hasNext()) {
                current2 = c2.next();
                LocalTime a = current.getStartTime();
                LocalTime b = current2.getStartTime();
                LocalTime c = current.getEndTime();
                LocalTime d = current2.getEndTime();

                boolean timeConflict = chkIfTimeConflict(a, b, c, d);

                if (current.getDay().equals(current2.getDay()) && timeConflict){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean chkIfTimeConflict(LocalTime start, LocalTime start2, LocalTime end, LocalTime end2) {
        if ((start.compareTo(start2) > 0) && (start.compareTo(end2)) < 0) {
            return true;
        } else if ((end.compareTo(start2) > 0) && (end.compareTo(end2) < 0)) {
            return true;
        } else if ((end.compareTo(end2) == 0) && (start.compareTo(start2) == 0)) {
            return true;
        } else return (start.compareTo(start2) > 0) && (end.compareTo(end2) < 0);
    }



}
