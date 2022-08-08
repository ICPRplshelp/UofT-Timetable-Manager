package org.example.logincode.usecases;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.Permissions;

import java.util.Date;

public class AdminAccountManager {

    private final Account account;
    private final StorageManager storageManager;


    /**
     * Constructs this class.
     *
     * @param controlledAccount        the admin account
     * @param storageManager the account storage which the admin may
     *                       do stuff to.
     */
    public AdminAccountManager(Account controlledAccount, StorageManager storageManager) {
        this.account = controlledAccount;
        this.storageManager = storageManager;
    }

    /**
     * The following methods should go into AdminAccountManager / alternative name when it is created.
     */
    public boolean createNewAdminUser(String username, String password) {
        if (account.getPermissions().hasPerm("admin")) {
            AccountCreator tempSystem = new AccountCreator(storageManager);
            Account candidateAccount = tempSystem.createAccount(username, password);
            if (candidateAccount == null) {
                return false;
            }  // false if the user exists, true otherwise
            Permissions tempPerm = candidateAccount.getPermissions();
            tempPerm.addPerm("admin");
            //accountStorageManager.loadedStorage.updateAccount(candidateAccount);
            return true;
        }
        return false;  // no perms
    }


    public boolean addPermission(String username, String permission) {
        if (account.getPermissions().hasPerm("admin")) {
            if (!storageManager.checkAccountExists(username)) {
                return false; // this user does not exist!
            }
            Account targetAccount = storageManager.getAccount(username);
            Permissions tempPerm = targetAccount.getPermissions();
            tempPerm.addPerm(permission); // permission input must be in camelCase
            //accountStorageManager.loadedStorage.updateAccount(targetAccount);
            return true;
        } else {
            return false; // tentative: you do not have the permissions to do this!
            // (Figure out if you need two error messages??)
        }
    }

    public boolean banUser(String username, Date unbanDate) {
        if (account.getPermissions().hasPerm("canBanUser")) {
            if (!storageManager.checkAccountExists(username)) {
                return false; // this user does not exist!
            }
            Account banTarget = storageManager.getAccount(username);
            if (banTarget.getPermissions().hasPerm("admin") || account == banTarget) {
                return false; // you can't ban an admin or yourself!
            }
            banTarget.getBanStatus().ban(unbanDate);
            //accountStorageManager.loadedStorage.updateAccount(banTarget);
            return true; // this user has been banned until (date)
        } else {
            return false; // tentative: you do not have the permissions to do this!
        }
    }

    public boolean deleteUser(String username) {
        if (account.getPermissions().hasPerm("admin")) {
            if (storageManager.checkAccountExists(username)) {
                Account deletionTarget = storageManager.getAccount(username);
                if ((deletionTarget.getPermissions().hasPerm("admin") || account == deletionTarget)) {
                    return false;  // you tried to ban yourself or another admin.
                }
                // if (account.permissionLevel() > banTarget.permissionLevel()) - tentative
                return storageManager.removeAccount(deletionTarget);

                // return true; // this account has been deleted.
            } else {
                return false; // this user does not exist!
            }
        } else {
            return false; // tentative: you do not have the permissions to do this!
        }
    }
}
