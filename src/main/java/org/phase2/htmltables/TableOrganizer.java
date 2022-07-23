package org.phase2.htmltables;

import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class TableOrganizer {

    private final char session;

    public TableOrganizer(char session) {
        this.session = session;
    }

    /**
     * Generates the raw HTML table body for all five days.
     *
     * @param ses a collection of IScheduleEntry (likely all of what the student
     *            has chosen) NO CLASS MAY CONFLICT OR START/END AT XX:30
     * @return a 5x24 array which represents the table - that
     * needs to be transposed before being passed
     * anywhere else.
     */
    public HTMLTableCell[][] genRawTableBody(Collection<IScheduleEntry> ses) {
        Collection<IScheduleEntry> moIse = new HashSet<>();
        Collection<IScheduleEntry> tuIse = new HashSet<>();
        Collection<IScheduleEntry> weIse = new HashSet<>();
        Collection<IScheduleEntry> thIse = new HashSet<>();
        Collection<IScheduleEntry> frIse = new HashSet<>();
        for (IScheduleEntry se : ses) {
            switch (se.getDay()) {
                case MONDAY -> moIse.add(se);
                case TUESDAY -> tuIse.add(se);
                case WEDNESDAY -> weIse.add(se);
                case THURSDAY -> thIse.add(se);
                case FRIDAY -> frIse.add(se);
            }
        }
        List<Collection<IScheduleEntry>> daysIse = List.of(moIse, tuIse, weIse, thIse, frIse);
        HTMLTableCell[][] toReturn = new HTMLTableCell[5][24];
        int i = 0;
        for (Collection<IScheduleEntry> daysIs : daysIse) {
            toReturn[i] = generateDayStrip(daysIs);
            i++;
        }
        return toReturn;
    }

    /**
     * Generate timetable cells for a single day. Each
     * index of what is returned represents the hour in a 24-hour clock.
     *
     * @param ises a collection of IScheduleEntries
     * @return check description
     */
    public HTMLTableCell[] generateDayStrip(Collection<IScheduleEntry> ises) {
        HTMLTableCell[] cellArray = new HTMLTableCell[24];
        Arrays.fill(cellArray, new FreeCell());
        for (IScheduleEntry ise : ises) {
            int startHour = ise.getStartTime().getHour();
            int endHour = ise.getEndTime().getHour();
            if (!cellArray[startHour].isEmpty()) {
                throw new RuntimeException("Course conflict??? head");
            }
            cellArray[startHour] = new CourseCell(ise, session);
            for (int i = startHour + 1; i < endHour; i++) {
                if (!cellArray[startHour].isEmpty()) {
                    throw new RuntimeException("Course conflict??? tail");
                }
                cellArray[i] = new OccupiedCell();
            }
        }
        return cellArray;
    }

}
