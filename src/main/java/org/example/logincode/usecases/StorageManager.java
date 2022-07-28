package org.example.logincode.usecases;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageManager {
    private static final Logger LOGGER = Logger.getLogger(StorageManager.class.getName());

    public AccountStorage accountStorage;
    Gateway loadedStorage;

    /**
     * Construct an AccountStorage with existing accounts.
     *
     * @param accounts K/V pairs of usernames mapped to existing account information.
     */
    public StorageManager(Collection<Account> accounts) {
        this.accountStorage = new AccountStorage();
        for (Account acc : accounts) {
            this.accountStorage.addAccount(acc);
        }


    }

    /**
     * Attempt to initialize this class with StorageLoader.
     */
    public StorageManager(Gateway loadedStorage) {
        this.loadedStorage = loadedStorage;
        try {
            this.accountStorage = this.loadedStorage.attemptLoad("accountInformation.ser");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Couldn't load anything. Perhaps the program was run for the first time, " +
                    "or a change was made to the structure of the account " +
                    "storage? Anyways, we're resetting all user storage " +
                    "so we're starting blank.");
            //this.loadedStorage = new StorageLoader();
        }

        if (this.accountStorage == null) {
            this.accountStorage = new AccountStorage();
        }
    }

    /**
     * Checks if the specified account exists by username.
     *
     * @param username The account's username to check.
     * @return Whether that username exists in the account system.
     */
    public boolean checkAccountExists(String username) {
        return accountStorage.checkAccountExists(username);
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
        return accountStorage.removeAccount(username);
    }

    /**
     * Tries to remove the account from the storage.
     * An account removal is successful if the account
     * with the username exists.
     *
     * @param account the account to remove.
     * @return whether the account removal was successful
     */
    public boolean removeAccount(Account account) {
        return removeAccount(account.getUsername());
    }

    /**
     * Tries to add the account to the storage.
     * If the username already exists, the account
     * will not be added.
     *
     * @param account the information about the account
     * @return whether the account addition was successful.
     * The only reason why account addition may fail is that
     * another account with the same username already exists.
     */
    public boolean addAccount(Account account) {
        return accountStorage.addAccount(account);
    }

    public boolean updateAccount(Account account) {
        boolean success = accountStorage.removeAccount(account.getUsername());
        if (success) {
            success = accountStorage.addAccount(account);
        }
        return success;
    }


    /**
     * Attempts to return the account with the username from the storage.
     *
     * @param username the username of the account to fetch.
     * @return the Account information, or null if the account does not exist.
     */
    public Account getAccount(String username) {
        return accountStorage.getAccount(username);
    }

    public void updateSave() {
        loadedStorage.updateAccounts(accountStorage);
    }

}
