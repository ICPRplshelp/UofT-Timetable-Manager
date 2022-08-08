package org.phase2.htmltables;

import org.example.timetable.entities.WarningType;
import org.phase2.studentrelated.presenters.IScheduleEntry;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.*;

public class TableOrganizer {

    private final char fallWinter;
    private final TableGenerator tableGenerator = new TableGenerator();
    private final WarningChecker2 wc;
    /**
     * Initiates this class, which is used to organize and
     * create the HTML output of a student's timetable.
     *
     * @param fallWinter whether a fall timetable or a winter timetable
     *                   is being generated ("F"/"S" term).
     *                   Default "S"
     */
    public TableOrganizer(char fallWinter,
                          WarningChecker2 wc) {
        this.fallWinter = fallWinter;
        this.wc = wc;
    }

    /**
     * It could never make a table after all.
     * @return the failure message
     */
    public String getHTMLFailure() {
        return "You tried to generate a table with a course conflict" +
                    "\nor a course that had a meeting that didn't start or end right on the hour";

    }

    /**
     * Generates an HTML string of a student's timetable (F/S term, but
     * not both).
     *
     * @param cells a collection os IScheduleEntry objects, which will all
     *              be placed in the timetable.
     *              NO CLASS MAY CONFLICT OR START/END AT XX:30
     * @return the HTML string of the table, including the table tag.
     * Use this HTML string on one of Java Swing's labels to display the table.
     */
    public String generateHTMLTable(Collection<IScheduleEntry> cells) {
        HTMLTableCell[][] rawTableBody = transposeTable(genRawTableBody(cells));
        HTMLTableCell[] head = generateHead();
        return "<table border=\"1\">" +
                tableGenerator.generateInnerHTMLTable(head, rawTableBody)
                + "</table>";
    }

    /**
     * Transposes the table
     * code based on <a href="https://stackoverflow.com/a/26199060">...</a>
     *
     * @param table the 2d array
     * @return the transposed table
     */
    private HTMLTableCell[][] transposeTable(HTMLTableCell[][] table) {
        HTMLTableCell[][] transposedTable = new HTMLTableCell[table[0].length][table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                transposedTable[j][i] = table[i][j];
            }
        }
        return transposedTable;
    }


    /**
     * Generates the raw HTML table body for all five days.
     *
     * @param ses a collection of IScheduleEntry (likely all of what the student
     *            has chosen) NO CLASS MAY CONFLICT OR START/END AT XX:30
     * @return a 6x24 array which represents the table - that
     * needs to be transposed before being passed
     * anywhere else.
     */
    private HTMLTableCell[][] genRawTableBody(Collection<IScheduleEntry> ses) {
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
        HTMLTableCell[][] toReturn = new HTMLTableCell[6][24];
        toReturn[0] = generate24HrClockStrip();
        int i = 1;
        for (Collection<IScheduleEntry> daysIs : daysIse) {
            toReturn[i] = generateDayStrip(daysIs);
            i++;
        }
        return toReturn;
    }

    /**
     * Generate timetable cells for a single day.
     * Each index of what is returned represents the hour at
     * a 24-hour clock.
     * <p>
     * No cell may conflict or start on an XX:30.
     *
     * @param ises a collection of IScheduleEntries
     * @return check description
     */
    private HTMLTableCell[] generateDayStrip(Collection<IScheduleEntry> ises) {
        HTMLTableCell[] cellArray = new HTMLTableCell[24];
        Arrays.fill(cellArray, new FreeCell());
        for (IScheduleEntry ise : ises) {
            int startHour = ise.getStartTime().getHour();
            int endHour = ise.getEndTime().getHour();
            if (ise.getStartTime().getMinute() != 0 || ise.getEndTime().getMinute() != 0) {
                throw new NotOnHourException();
            }
            if (!cellArray[startHour].isEmpty()) {
                throw new ConflictException();
            }
            Set<WarningType> crWarnings = wc.getLastMap().get(ise);
            cellArray[startHour] = new CourseCell(ise, fallWinter, crWarnings);
            startHour += 1;
            for (int i = startHour; i < endHour; i++) {
                if (!cellArray[i].isEmpty()) {
                    // System.out.println(startHour);
                    throw new ConflictException();
                }
                cellArray[i] = new OccupiedCell();
            }
        }
        return cellArray;
    }

    /**
     * Generates an array of 24 ClockCells, each representing an hour.
     *
     * @return check the description above
     */
    private HTMLTableCell[] generate24HrClockStrip() {
        HTMLTableCell[] toReturn = new HTMLTableCell[24];
        for (int i = 0; i < 24; i++) {
            toReturn[i] = new ClockCell(i);
        }
        return toReturn;
    }

    /**
     * Generates the head of the timetable thing.
     *
     * @return The head.
     */
    private HTMLTableCell[] generateHead() {
        HTMLTableCell[] toReturn = new HTMLTableCell[6];
        for (int i = 0; i < 6; i++) {
            toReturn[i] = new HeaderCell(i);
        }
        return toReturn;
    }
}
