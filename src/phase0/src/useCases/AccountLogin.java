package useCases;

import entities.Account;

public class AccountLogin {

    StorageManager accountStorageManager;

    /**
     * Construct this class.
     *
     * @param accountStorageManager The storage manager to look for accounts.
     */
    public AccountLogin(StorageManager accountStorageManager) {
        this.accountStorageManager = accountStorageManager;
    }

    /**
     * Attempt to log into the account.
     * If a login is successful, return the account
     * and record its login at the current date.
     *
     * @param username the username to log into
     * @param password the password to test
     * @return the Account, or null if the password is incorrect or the account DNE
     */
    public Account login(String username, String password) {
        if (!accountStorageManager.checkAccountExists(username)) {
            return null;
        }
        // then the account exists.
        Account tempAccount = accountStorageManager.getAccount(username);
        // verify that the password is correct. If not, return null.
        if (!tempAccount.getPassword().equals(password)) {
            return null;
        }
        tempAccount.addNowToAccountHistory();
        accountStorageManager.updateAccount(tempAccount);
        return tempAccount;
    }


    /**
     * Return true if the username-password combination is correct.
     * Note that if true is returned, its account history
     * will log a successful login.
     * <p>
     * Banned accounts cannot log in.
     */
    public boolean validateLogin(String username, String password) {
        // check if account actually exists before calling getBanStatus
        if (login(username, password) == null) {
            return false;
        }
        // the guard clause for if an account is banned.
        return !accountStorageManager.getAccount(username).getBanStatus().isBanned();
    }
}
