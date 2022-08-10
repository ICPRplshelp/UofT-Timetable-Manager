package org.example.studentrelated.usecases;

import org.example.coursegetter.entities.baseclasses.Course;
import org.example.timetable.entities.WarningType;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcherPrev;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class FYFWarningAdder implements WarningAdder {


    /**
     * Adds all FYF-related warnings to the warn list

     * @param planned all the student's planned courses
     * @param passed all the student's passed courses
     * @param warnList the current warn list so far
     */
    public void addWarnings(Set<String> planned, Set<String> passed,
                     Map<String, Set<WarningType>> warnList){
        UsableCourseSearcher csa = UsableCourseSearcher.getInstance();
        UsableCourseSearcherPrev csap = UsableCourseSearcherPrev.getInstance();
        long halfCreditsCompleted = calculateCompletedCredits(passed, csap);
        if(halfCreditsCompleted < 8){
            return;
        }
        addWarningsHelper(warnList, planned, csa);
    }

    private void addWarningsHelper(Map<String, Set<WarningType>> warnList, Set<String> planned, UsableCourseSearcher csa) {
        for(String c : planned) {
            Course cr = csa.getCourse(c);
            if (cr == null || !cr.firstYearOnly()) {
                continue;
            }
            if(!warnList.containsKey(c)) warnList.put(c, new HashSet<>());
            Set<WarningType> cwl = warnList.get(c);
            cwl.add(WarningType.FYF);
        }
    }

    private long calculateCompletedCredits(Set<String> passed, UsableCourseSearcherPrev csap) {
        double creditsCompleted = 0.0;
        for (String pc : passed) {
            Course pc2 = csap.getCourse(pc);
            if (pc2 != null) {
                creditsCompleted += pc2.getCreditValue();
            }
        }
        return Math.round(creditsCompleted * 2);
    }
}
