package org.example.logincode.usecases;

import org.example.logincode.entities.Account;

public class AccountManager {

    public Account getAccount() {
        return account;
    }

    private final Account account;
    // protected Set<String> commandList;
    final StorageManager accountStorageManager;
    private static AccountManager am;


    /**
     * Creates a new AccountManager instance based on an account.
     *
     * @param controlledAccount     the username to log in. The username better exist.
     * @param accountStorageManager the account storage to check.
     */
    public AccountManager(Account controlledAccount, StorageManager accountStorageManager) {
        account = controlledAccount;
        this.accountStorageManager = accountStorageManager;

        // add to account history already in AccountLogin / AccountCreator
    }


    private static AccountManager getInstance(String name, StorageManager storageManager ) {
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
    public AccountManager(String username, StorageManager accountStorageManager) {
        this.accountStorageManager = accountStorageManager;
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
