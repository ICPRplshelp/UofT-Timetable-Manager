package org.example.logincode.controllerspresentersgateways.controllers;

import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.AdminAccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.Date;

public class ControllerAdmin {

    private final AdminAccountManager adminAccountManager;

    public ControllerAdmin(AccountManager manager, StorageManager accountStorageManager) {
        adminAccountManager = new AdminAccountManager(manager.getAccount(), accountStorageManager);
    }

    public boolean promoteUserToAdmin(String userToPromote) {

        return adminAccountManager.addPermission(userToPromote, "admin");
    }

    public boolean createNewAdminUser(String[] credentials) {

        return adminAccountManager.createNewAdminUser(credentials[0], credentials[1]);

    }

    public boolean deleteUser(String userToDelete) {
        return adminAccountManager.deleteUser(userToDelete);
    }

    public boolean banUser(String userToBan, Date unbanDate) {
        return adminAccountManager.banUser(userToBan, unbanDate);
    }
}
