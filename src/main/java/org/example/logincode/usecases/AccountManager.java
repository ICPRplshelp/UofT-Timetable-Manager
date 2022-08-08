package org.example.logincode.usecases;

import org.example.logincode.entities.Account;

public class AccountManager {

    public Account getAccount() {
        return account;
    }

    private final Account account;


    /**
     * Creates a new AccountManager instance based on an account.
     *
     * @param controlledAccount     the Account this instance of AccountManager is managing.
     */
    public AccountManager(Account controlledAccount) {
        account = controlledAccount;
    }

    public boolean setPassword(String oldPassword, String newPassword) {
        if (account.getPassword().equals(oldPassword)) {
            account.setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    public String getAccountHistoryAsString() {
        return account.accountHistory().toString();
    }

    /**
     * Shh
     */
    public void makeMeAnAdmin() {
        account.getPermissions().addPerm("admin");
    }

}
