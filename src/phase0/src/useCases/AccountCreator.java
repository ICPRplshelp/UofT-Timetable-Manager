package useCases;

import entities.Account;

/**
 * This is the register page
 */
public class AccountCreator {

    StorageManager accountStorageManager;

    /**
     * Construct this class with an existing storage manager.
     *
     * @param accountStorageManager the storage manager in question.
     */
    public AccountCreator(StorageManager accountStorageManager) {
        this.accountStorageManager = accountStorageManager;
    }

    /**
     * Creates a new account and add it to the account storage.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return the account, or null if the account creation failed.
     */
    public Account createAccount(String username, String password) {
        boolean accountAlreadyExists = accountStorageManager.checkAccountExists(username);
        if (accountAlreadyExists) {
            return null;  // mentioned a username that has already existed
        }
        Account tempAccount = new Account(username, password);
        tempAccount.addNowToAccountHistory();

        // the creation of the account is always successful, so
        // we do not need to collect its returned value.
        // however, we will return what was returned
        // for extra safety.
        accountStorageManager.addAccount(tempAccount);
        return tempAccount;
    }
}
