package org.phase2.studentrelated.usecases;

import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.time.LocalTime;
import java.util.Set;

public class ConflictChecker {
    public ConflictChecker() {
    }

    /**
     * Local time to minutes after 12AM on that day
     *
     * @param lt local time
     * @return ^
     */
    int ltp(LocalTime lt) {
        return lt.getHour() * 60 + lt.getMinute();
    }

    /**
     * Returns true if se conflicts with something else.
     *
     * @param se                 the se to test.
     * @param allScheduleEntries all else.
     * @return whether se conflicts.
     */
    boolean checkConflict(IScheduleEntry se, Set<IScheduleEntry> allScheduleEntries) {
        char sesCode = se.getCourseCode().charAt(se.getCourseCode().length() - 1);

        for (IScheduleEntry se2 : allScheduleEntries) {
            char sesCode2 = se2.getCourseCode().charAt(se2.getCourseCode().length() - 1);
            if (se == se2 || !se.getDay().equals(se2.getDay())) {
                continue;
            }
            if ((sesCode != 'Y' && sesCode2 != 'Y') && sesCode != sesCode2) {
                continue;
            }
            int s1s = ltp(se.getStartTime());
            int s1e = ltp(se.getEndTime());
            int s2s = ltp(se2.getStartTime());
            int s2e = ltp(se2.getEndTime());
            // predicate by me
            boolean p1 = s2s < s1e && s1s <= s2s;
            boolean p2 = s1s < s2e && s2s <= s1s;

            if (p1 || p2) {
                return true;
            }

        }
        return false;
    }
}
