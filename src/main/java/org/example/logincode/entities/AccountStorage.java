package org.example.logincode.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores all accounts.
 */
public class AccountStorage implements Serializable {
    private final Map<String, Account> accounts;
    private static AccountStorage as;


    /**
     * Construct a new AccountStorage with no accounts.
     */
    private AccountStorage() {
        this.accounts = new HashMap<>();
    }

    public static AccountStorage getInstance() {
        if (as == null) {
            as = new AccountStorage();
        }
        return as;
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
}
