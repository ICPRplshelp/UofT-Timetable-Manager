package org.example.logincode.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores all accounts.
 */
public class AccountStorage implements Serializable {
    private final Map<String, Account> accounts;


    /**
     * Construct a new AccountStorage with no accounts.
     */
    public AccountStorage() {
        this.accounts = new HashMap<>();
    }

    /**
     * Tries to add the account to the storage.
     * If the username already exists, the account
     * will not be added.
     *
     * @param account the information about the account
     */
    public void addAccount(Account account) {
        String username = account.getUsername();
        if (!accounts.containsKey(username)) {
            accounts.put(username, account);
        }
    }

    /**
     * Tries to remove the account from the storage.
     * An account removal is successful if the account
     * with the username exists.
     *
     * @param username the username of the account to remove
     * @return whether the account removal was successful
     */
    public boolean removeAccount(String username) {
        if (accounts.containsKey(username)) {
            accounts.remove(username);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the specified account exists by username.
     *
     * @param username The account's username to check.
     * @return Whether that username exists in the account system.
     */
    public boolean checkAccountExists(String username) {
        return accounts.containsKey(username);
    }


    /**
     * Attempts to return the account with the username from the storage.
     *
     * @param username the username of the account to fetch.
     * @return the Account information, or null if the account does not exist.
     */
    public Account getAccount(String username) {
        return accounts.get(username);
    }

}