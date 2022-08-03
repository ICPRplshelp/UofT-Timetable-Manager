package org.phase2.htmltables;

import org.example.timetable.entities.WarningType;
import org.jetbrains.annotations.NotNull;
import org.phase2.htmlutilities.HTMLListGen;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.*;

class CourseCell implements HTMLTableCell {

    private final String htmlText;
    private final HTMLListGen htmlListGen = new HTMLListGen();


    public CourseCell(IScheduleEntry se, char session, Set<WarningType> warnings) {
        int stHr = se.getStartTime().getHour();
        int enHr = se.getEndTime().getHour();
        if (se.getStartTime().getMinute() != 0 || se.getEndTime().getMinute() != 0) {
            throw new RuntimeException("A class in your timetable started" +
                    " or ended on a time other than the hour");
        }
        int rowSpan = enHr - stHr;
        StringBuilder sb = getCourseHTML(se, session, rowSpan,
                generateWLAsStringCollection(warnings));
        htmlText = sb.toString();
    }

    private @NotNull Collection<String> generateWLAsStringCollection(Set<WarningType> warningTypeSet){
        List<String> sf = new ArrayList<>();
        if(warningTypeSet == null) { return sf; }
        for(WarningType wts : warningTypeSet){
            switch(wts){
                case CONFLICT -> sf.add("CONFLICT");
                case DIST -> sf.add("DIST");
                default -> sf.add("?? ISSUE");
            }
        }
        return sf;
    }

    @NotNull
    private StringBuilder getCourseHTML(IScheduleEntry se, char session, int rowSpan,
                                        Collection<String> warningsAsString) {
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
        if(!warningsAsString.isEmpty()){
            sb.append("<br>");
            String htmlLi = htmlListGen.generateHTMLList(warningsAsString);
            sb.append(htmlLi);
        }

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
