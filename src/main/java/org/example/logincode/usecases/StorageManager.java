package org.example.logincode.usecases;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageManager {
    private static final Logger LOGGER = Logger.getLogger(StorageManager.class.getName());

    private static AccountStorage accountStorage;
    public static IGateway loadedStorage;
    private static StorageManager sm;

    public static StorageManager getInstance() {
        return sm;
    }

    public static StorageManager getInstance(IGateway loadedStorage) {
        if (accountStorage == null) {
            sm = new StorageManager(loadedStorage);
        }
        return sm;
    }
    /**
     * Attempt to initialize this class with StorageLoader.
     */


    private StorageManager(IGateway loadedStorage) {
        StorageManager.loadedStorage = loadedStorage;

        try {
            accountStorage = StorageManager.loadedStorage.attemptLoad("accountInformation.ser");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't load anything, perhaps the program was run for the first time? Double check, in your run configurations, that the current working directory is $MODULE_WORKING_DIR$ and not anything else.");

        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "Couldn't load anything, perhaps the program was run for the first time? A class that was serializable may have changed.");

        }


        if (this.getAccountStorage() == null) {
            accountStorage = AccountStorage.getInstance();
        }
    }

    /**
     * Checks if the specified account exists by username.
     *
     * @param username The account's username to check.
     * @return Whether that username exists in the account system.
     */
    public boolean checkAccountExists(String username) {
        return getAccountStorage().checkAccountExists(username);
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
        return getAccountStorage().removeAccount(username);
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
     */
    public boolean addAccount(Account account) {
        return getAccountStorage().addAccount(account);
    }

    public void updateAccount(Account account) {
        if (getAccountStorage().removeAccount(account.getUsername())) {
            getAccountStorage().addAccount(account);
        }
    }


    /**
     * Attempts to return the account with the username from the storage.
     *
     * @param username the username of the account to fetch.
     * @return the Account information, or null if the account does not exist.
     */
    public Account getAccount(String username) {
        return getAccountStorage().getAccount(username);
    }

    public void updateSave() {
        loadedStorage.updateAccounts(getAccountStorage());
    }

    public AccountStorage getAccountStorage() {
        return accountStorage;
    }
}
