package org.example.timetable.entities;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Timetable  implements Serializable {


    private final Collection<CourseChoice> plannedCourses;

    public Timetable (Collection<CourseChoice> plannedCourses){
        this.plannedCourses = plannedCourses;
    }

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();


    public boolean addToTimetable(CourseChoice courseChoice) {
        return plannedCourses.add(courseChoice);
    }

    public boolean removeFromTimetable(CourseChoice courseChoice) {
        return plannedCourses.remove(courseChoice);
    }

    /**
     * @return all planned course choices so far.
     */
    public Collection<CourseChoice> getPlannedCourses(){
        return plannedCourses;
    }

    public Collection<CourseChoice> getPlannedCourses(String section){
        Stream<CourseChoice> temp = plannedCourses.stream().filter(crs -> crs.getCourse().getSession().equals(section));
        return temp.collect(Collectors.toList());
    }

    public void addWarning(CourseChoice courseChoice, TimetableWarning timetableWarning){
        if (!warnings.containsKey(courseChoice)){
            warnings.put(courseChoice, new CourseWarning());
        }

        warnings.get(courseChoice).addWarning(timetableWarning);

    }

    public CourseWarning getWarning(CourseChoice courseChoice) {
        return warnings.get(courseChoice);
    }

    public void checkWarnings() {
        List<CourseChoice> plannedCoursesList = new ArrayList<>();
        plannedCoursesList = plannedCourses.stream().collect(Collectors.toList());

        Collection<String> coursesAsString = new ArrayList<>();

        for (int i = 0; i < plannedCoursesList.size(); i++) {
            coursesAsString.add(plannedCoursesList.get(i).getCourse().getCode());
            if (plannedCoursesList.get(i).getCourse().firstYearOnly()) {
                setWarningsHelper("FYF", plannedCoursesList.get(i));
            }

            for (int n = 0; n < plannedCoursesList.size(); n ++) {
                if (plannedCoursesList.get(i) != plannedCoursesList.get(n)) {
                    CourseChoice choice1 = plannedCoursesList.get(i);
                    CourseChoice choice2 = plannedCoursesList.get(n);
                    if (getCourseConflict(choice1, choice2)) {setWarningsHelper("CONFLICT", plannedCoursesList.get(i));}
                }
            }
        }

        for (CourseChoice courseChoice : plannedCoursesList) {
            RequisiteChecker checker = new RequisiteChecker();
            if (!checker.checkExclusions(coursesAsString, courseChoice.getCourse().getExclusion())) {
                setWarningsHelper("EXC", courseChoice);
            }
            if (!checker.check(coursesAsString, courseChoice.getCourse().getCorequisite())) {
                setWarningsHelper("CRQ", courseChoice);
            }
        }

    }

    private void setWarningsHelper(String Type, CourseChoice courseChoice) {
        TimetableWarning timetableWarning = new TimetableWarning() {};
        if (Objects.equals(Type, "EXC")) {
            timetableWarning.setWarningType(WarningType.EXC);
        } else if (Objects.equals(Type, "FYF")) {
            timetableWarning.setWarningType(WarningType.FYF);
        } else if (Objects.equals(Type, "CRQ")) {
            timetableWarning.setWarningType(WarningType.CRQ);
        } else if (Objects.equals(Type, "CONFLICT")) {
            timetableWarning.setWarningType(WarningType.CONFLICT);
        }

        addWarning(courseChoice, timetableWarning);

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

