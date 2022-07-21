package org.example.timetable.entities.warningtypes;

public class YearOneWarning extends TimetableWarning{

    public YearOneWarning(){

        warningType = WarningType.FYF;
    }

    @Override
    public String toString(){
        return "Year 1 only course";
    }

}
