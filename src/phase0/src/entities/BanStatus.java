package entities;

import java.io.Serializable;
import java.util.Date;

import static entities.Today.getToday;

public class BanStatus implements ReprAble, Serializable {
    private Date bannedUntil;

    public String toString() {
        return bannedUntil.toString();
    }

    /**
     * Returns the time the user is banned until
     * in the form of seconds since 1/1/1970 GMT.
     *
     * @return See description
     */
    private long bannedUntilSeconds() {
        return bannedUntil.getTime();
    }

    public String repr() {
        if (bannedUntil == null) {
            return "0";
        }
        return Long.toString(bannedUntilSeconds());
    }

    public void setFromRepr(String reprString) {
        this.bannedUntil = null;
        if (!reprString.equals("0")) {
            this.bannedUntil = new Date(Long.parseLong(reprString));
        }
    }

    public BanStatus() {
        bannedUntil = null;
    }

    public Date getBannedUntil() {
        return bannedUntil;
    }

    public boolean isBanned() {
        return this.bannedUntil != null && this.bannedUntil.after(getToday());
    }

    /**
     * Set the banned date to a specified date.
     *
     * @param until the time the ban is to be lifted.
     */
    public void ban(Date until) {
        this.bannedUntil = until;
    }

    public void unban() {
        this.bannedUntil = null;
    }
}
