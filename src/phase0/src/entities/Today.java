package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Today {

    /**
     * Get the time right now, up to the millisecond.
     *
     * @return The time this method was executed.
     */
    public static Date getToday() {
        // https://stackoverflow.com/a/5046804
        Calendar today = Calendar.getInstance();
        // today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
        return today.getTime();
    }
}
