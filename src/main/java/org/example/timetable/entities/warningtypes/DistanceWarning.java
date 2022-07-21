package org.example.timetable.entities.warningtypes;

public class DistanceWarning extends TimetableWarning{

    public DistanceWarning(){
        warningType = WarningType.DIST;
    }

    @Override
    public String toString(){
        return "Long travel dist. with previous crs";
    }

}
