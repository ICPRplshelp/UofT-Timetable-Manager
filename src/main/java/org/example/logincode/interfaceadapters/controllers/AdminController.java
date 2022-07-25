package org.example.logincode.interfaceadapters.controllers;

import org.example.logincode.usecases.AdminAccountManager;

import java.util.Date;

public class AdminController {
    private final AdminAccountManager adminAccountManager;

    public AdminController(AdminAccountManager adminAccountManager) {
        this.adminAccountManager = adminAccountManager;
    }

    public boolean banUser(String userToBan, Date unbanDate){
        return adminAccountManager.banUser(userToBan, unbanDate);
    }

    public boolean deleteUser(String userToDelete){
        return adminAccountManager.deleteUser(userToDelete);
    }

    public boolean createNewAdminUser(String username, String password){
        return adminAccountManager.createNewAdminUser(username, password);
    }

    public boolean promoteUserToAdmin(String username){
        return adminAccountManager.addPermission(username, "admin");
    }

}
