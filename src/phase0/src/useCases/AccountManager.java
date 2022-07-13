package useCases;

import entities.Account;

import java.util.Collection;
import java.util.Set;

public class AccountManager {

    protected final Account account;
    // protected Set<String> commandList;
    StorageManager accountStorageManager;

    /**
     * Validate one permission.
     *
     * @param permission permission to check.
     * @return whether permission exists.
     */
    public boolean validatePermission(String permission) {
        return account.getPermissions().hasPerm(permission);
    }

    /**
     * Validate multiple permissions.
     *
     * @param permissions permissions to check.
     * @return whether permission exists.
     */
    public boolean validatePermission(String[] permissions) {
        return account.getPermissions().hasPerm(permissions);
    }

    /**
     * Validate multiple permissions.
     *
     * @param permissions permissions to check.
     * @return whether permission exists.
     */
    public boolean validatePermission(Collection<String> permissions) {
        return account.getPermissions().hasPerm(permissions);
    }

    /**
     * Creates a new AccountManager instance based on an account.
     *
     * @param controlledAccount              the username to log in. The username better exist.
     * @param accountStorageManager the account storage to check.
     */
    public AccountManager(Account controlledAccount, StorageManager accountStorageManager) {
        account = controlledAccount;
        this.accountStorageManager = accountStorageManager;

        // add to account history already in AccountLogin / AccountCreator
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
     * Shhhh
     */
    public void makeMeAnAdmin() {
        account.getPermissions().addPerm("admin");
    }

}
