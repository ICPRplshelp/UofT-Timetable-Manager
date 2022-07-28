package org.phase2.studentrelated.presenters;

import org.example.timetable.entities.warningtypes.WarningType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;

public class WarningPresenter {

    private final EnumMap<WarningType, String> warnToStr;
    private final String wStr = "Warning: ";

    {
        warnToStr = new EnumMap<>(WarningType.class);
        warnToStr.put(WarningType.CONFLICT, "your course has a conflict");
        warnToStr.put(WarningType.PRQ, "your course is missing prerequisites");
        warnToStr.put(WarningType.CRQ, "your course is missing corequisites");
        warnToStr.put(WarningType.EXC, "you passed or will be concurrently taking an exclusion to this course");
        warnToStr.put(WarningType.FYF, "your course is for first-year students only and you aren't one");
        warnToStr.put(WarningType.DIST, "you'll collapse running to a lecture/tutorial/practical in this course");
        warnToStr.put(WarningType.UNKNOWN, "there is some other unknown issue");
    }

    /**
     * Given a collection of warnings, this method generates a human-
     * readable string that explains the warnings in more detail.
     * @param warnings a collection of warnings.
     * @return check description.
     */
    public String getWarningsAsString(Collection<WarningType> warnings) {
        List<String> warnStrsSoFar = new ArrayList<>();
        for (WarningType w : warnings) {
            warnStrsSoFar.add(warnToStr.get(w));
        }
        if (warnStrsSoFar.size() > 2) {
            warnStrsSoFar.set(warnStrsSoFar.size() - 1, " and " + warnStrsSoFar.get(warnStrsSoFar.size() - 1));
        }
        if (warnStrsSoFar.size() == 2) {
            return "Warning: " + String.join(" and ", List.of(warnStrsSoFar.get(0), warnStrsSoFar.get(1)));
        }

        if (warnStrsSoFar.size() == 1) {
            return "Warning: " + warnStrsSoFar.get(0);
        }
        return "Warning: " + String.join(", ", warnStrsSoFar);
    }
}
