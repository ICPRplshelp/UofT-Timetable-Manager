package org.phase2.htmltables;

import org.jetbrains.annotations.NotNull;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.Objects;

class CourseCell implements HTMLTableCell {

    private final String htmlText;


    public CourseCell(IScheduleEntry se, char session){
        int stHr = se.getStartTime().getHour();
        int enHr = se.getEndTime().getHour();
        if(se.getStartTime().getMinute() != 0 || se.getEndTime().getMinute() != 0){
            throw new RuntimeException("A class in your timetable started" +
                    " or ended on a time other than the hour");
        }
        int rowSpan = enHr - stHr;
        StringBuilder sb = getCourseHTML(se, session, rowSpan);
        htmlText = sb.toString();
    }

    @NotNull
    private StringBuilder getCourseHTML(IScheduleEntry se, char session, int rowSpan) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<td rowspan=\"%d\">", rowSpan));
        sb.append(se.getCourseCode()).append("<br>");
        sb.append(se.getMeetingCode()).append("<br>");
        sb.append(se.getStartTime().toString());
        sb.append("-");
        sb.append(se.getEndTime().toString());
        sb.append("<br>");
        sb.append(session == 'F' ?
                Objects.requireNonNullElse(se.getAssignedRoom1(), "") :
                Objects.requireNonNullElse(se.getAssignedRoom2(), ""));
        sb.append("</td>");
        return sb;
    }

    @Override
    public String getHTMLText() {
        return htmlText;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
