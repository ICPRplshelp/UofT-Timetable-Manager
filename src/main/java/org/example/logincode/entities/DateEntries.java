package org.example.logincode.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateEntries implements ReprAble, Serializable {
    private final List<Date> dates;

    /**
     * Construct this class.
     */
    public DateEntries() {
        this.dates = new ArrayList<>();
    }

    public String toString() {
        return dates.toString();
    }


    public void addDate(Date curDate) {
        dates.add(curDate);
    }


}
