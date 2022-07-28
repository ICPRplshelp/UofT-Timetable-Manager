package org.example.logincode.entities;

import java.io.Serializable;
import java.util.Date;

import static org.example.logincode.entities.Today.getToday;

public class BanStatus implements Serializable {
    private Date bannedUntil;

    public String toString() {
        return bannedUntil.toString();
    }

    public BanStatus() {
        bannedUntil = null;
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

}
