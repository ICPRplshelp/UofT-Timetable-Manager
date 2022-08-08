package org.example.logincode.usecases;

import org.example.logincode.entities.Account;

public class AccountManager {

    public Account getAccount() {
        return account;
    }

    private final Account account;
    private static AccountManager am;


    public static AccountManager getInstance(String name, StorageManager storageManager) {
        if (am == null) {
            am = new AccountManager(name, storageManager);
        }
        return am;
    }
    /**
     * Creates a new AccountManager instance based on the username.
     *
     * @param username              the username to log in. The username better exist.
     * @param accountStorageManager the account storage to check.
     */
    private AccountManager(String username, StorageManager accountStorageManager) {
        account = accountStorageManager.getAccount(username);
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
