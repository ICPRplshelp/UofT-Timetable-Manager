package org.example.timetable.entities.warnings;

abstract public class TimetableWarning {
    protected String severity = "null warning";

    protected abstract String warningInfo();

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getWarning(){
        return "[" + severity.toUpperCase() + ":" + warningInfo() + "]";
    }


}
