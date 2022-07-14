package org.example.logincode.entities;

import org.example.studentdata.Student;

import java.io.Serializable;

public class Account implements Serializable{
    // usernames must be unique.
    private final String username;
    private final BanStatus banStatus;
    private final Permissions permissions;
    //    protected int permissionLevel;
    private String password;
    private final DateEntries accountHistory;

    // I'm using student here because it seems like Student is storing all the data that a 'StudentData' entity should have.
    private Student student;

    /**
     * Constructs the account.
     * Constructing the account does NOT log anything,
     * so external controllers must log it themselves.
     *
     * @param username The username
     * @param password The password
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.banStatus = new BanStatus();
        this.accountHistory = new DateEntries();
        this.permissions = new Permissions();
//        this.permissionLevel = 0;  // the default permission level is 0
    }

    public Account(String username, String password,
                   BanStatus banStatus, DateEntries accountHistory,
                   Permissions permissions) {
        this.username = username;
        this.password = password;
        this.banStatus = banStatus;
        this.accountHistory = accountHistory;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    // public String setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DateEntries accountHistory() {
        return accountHistory;
    }


    /**
     * Any methods run on the ban status
     * that is returned will affect
     * the account directly.
     *
     * @return the account's ban status.
     */
    public BanStatus getBanStatus() {
        return banStatus;
    }

    public Permissions getPermissions() {
        return permissions;
    }


    /**
     * Add the current time right now to the account history.
     */
    public void addNowToAccountHistory() {
        this.accountHistory.addDate(Today.getToday());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}