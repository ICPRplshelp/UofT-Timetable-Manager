package org.example.studentrelated.presenters;

import org.example.timetable.entities.WarningType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;

public class WarningPresenter {

    private final EnumMap<WarningType, String> warnToStr;

    {
        warnToStr = new EnumMap<>(WarningType.class);
        warnToStr.put(WarningType.CONFLICT, ">=1 conflict");
        warnToStr.put(WarningType.PRQ, "missing prerequisites");
        warnToStr.put(WarningType.CRQ, "missing corequisites");
        warnToStr.put(WarningType.EXC, "you have exclusions");
        warnToStr.put(WarningType.FYF, "first year only");
        warnToStr.put(WarningType.DIST, ">=1 prev. course too far");
        warnToStr.put(WarningType.UNKNOWN, "there is some other unknown issue");
        warnToStr.put(WarningType.TAKEN, "you have/will have taken this course");
        warnToStr.put(WarningType.MISSING_LEC, "missing lecture");
        warnToStr.put(WarningType.MISSING_TUT, "missing tutorial");
        warnToStr.put(WarningType.MISSING_PRA, "missing practical");
    }

    /**
     * Given a collection of warnings, this method generates a human-
     * readable string that explains the warnings in more detail.
     *
     * @param warnings a collection of warnings.
     * @return check description.
     */
    public String getWarningsAsString(Collection<WarningType> warnings) {
        List<String> warnStrsSoFar = new ArrayList<>();
        for (WarningType w : warnings) {
            warnStrsSoFar.add(warnToStr.get(w));
        }
        if (warnStrsSoFar.size() > 2) {
            warnStrsSoFar.set(warnStrsSoFar.size() - 1, "and " + warnStrsSoFar.get(warnStrsSoFar.size() - 1));
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
