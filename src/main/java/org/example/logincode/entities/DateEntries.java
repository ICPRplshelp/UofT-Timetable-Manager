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

    /**
     * The only reason why dates should be cleared is that
     * it was set from repr.
     */
    private void clearDates() {
        dates.clear();
    }

    /**
     * Get the representation of this class in the form of
     * MSSINCE1970:MSSINCE1970:MSSINCE1970, or an empty string if no
     * dates are logged.
     *
     * @return What is stated above.
     */
    public String repr() {
        List<Long> msListSoFar = new ArrayList<>();
        this.dates.forEach(curDate -> msListSoFar.add(curDate.getTime()));
        List<String> msListString = new ArrayList<>();
        msListSoFar.forEach(msEntry -> msListString.add(msEntry.toString()));
        StringBuilder finalString = new StringBuilder();
        msListString.forEach(tempStr -> finalString.append(tempStr).append(":"));
        if (!finalString.isEmpty()) {
            finalString.deleteCharAt(finalString.length() - 1);
        }
        return finalString.toString();
    }

    public void setFromRepr(String reprString) {
        clearDates();
        String[] dateStrings = reprString.split(":");
        for (String dateString : dateStrings) {
            addDate(new Date(Long.parseLong(dateString)));
        }
    }


}
