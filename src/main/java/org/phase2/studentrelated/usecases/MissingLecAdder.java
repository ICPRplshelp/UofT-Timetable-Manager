package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.TeachingMethods;
import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meetings;
import org.example.timetable.entities.WarningType;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcher;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class MissingLecAdder implements WarningAdder {
    UsableCourseSearcher plannedSearcher;

    Map<String, Set<String>> planned;

    public MissingLecAdder(UsableCourseSearcher ucs,
                           Map<String, Set<String>> planned) {
        plannedSearcher = ucs;
        this.planned = planned;
    }

    /**
     * Adds all the missing lec/tut/pra warnings to the course.
     *
     * @param planned  set of planned courses
     * @param passed   set of passed courses
     * @param warnList the warn list
     */
    public void addWarnings(Set<String> planned, Set<String> passed,
                     Map<String, Set<WarningType>> warnList) {
        for (String pc : planned) {
            Set<TeachingMethods> state = checkMissingMeetings(pc);
            if (!state.isEmpty()) {
                if (!warnList.containsKey(pc)) {
                    warnList.put(pc, new HashSet<>());
                }
                Set<WarningType> pcWset = warnList.get(pc);
                if (state.contains(TeachingMethods.LEC)) {
                    pcWset.add(WarningType.MISSING_LEC);
                }
                if (state.contains(TeachingMethods.TUT)) {
                    pcWset.add(WarningType.MISSING_TUT);
                }
                if (state.contains(TeachingMethods.PRA)) {
                    pcWset.add(WarningType.MISSING_PRA);
                }
            }
        }
    }

    Set<TeachingMethods> checkMissingMeetings(String course) {
        Course crs = plannedSearcher.getCourse(course);
        if (crs == null) return null;
        Meetings met = crs.getMeetings();
        Set<String> meetingSet = planned.get(course);
        Set<TeachingMethods> existingMethods = buildExistingTeachingMethods(meetingSet);
        Set<TeachingMethods> requiredTMs = new HashSet<>();
        if (met.hasLectures() && !existingMethods.contains(TeachingMethods.LEC)) requiredTMs.add(TeachingMethods.LEC);
        if (met.hasTutorials() && !existingMethods.contains(TeachingMethods.TUT)) requiredTMs.add(TeachingMethods.TUT);
        if (met.hasPracticals() && !existingMethods.contains(TeachingMethods.PRA)) requiredTMs.add(TeachingMethods.PRA);
        return requiredTMs;
    }

    Set<TeachingMethods> buildExistingTeachingMethods(Set<String> meetingSet) {
        Set<TeachingMethods> existingMethods = new HashSet<>();
        if(meetingSet == null) return existingMethods;
        for (String meeting : meetingSet) {
            String firstThree = meeting.substring(0, Math.min(3, meeting.length()));
            switch (firstThree) {
                case "LEC" -> existingMethods.add(TeachingMethods.LEC);
                case "TUT" -> existingMethods.add(TeachingMethods.TUT);
                case "PRA" -> existingMethods.add(TeachingMethods.PRA);
            }
        }
        return existingMethods;
    }
}
