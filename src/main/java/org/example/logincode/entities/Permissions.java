package org.example.logincode.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the permissions a user has.
 * Each permission is stored in a set of strings,
 * following the camelCase naming convention.
 * <p>
 * IMPORTANT: If "admin" is included as a permission,
 * the user that holds the permission class is
 * assumed to have all permissions.
 * <p>
 * Current Permissions: admin, canBanUser
 */
public class Permissions implements Serializable {
    // superclasses may access permissions - meaning only in this package.

    private final Set<String> permissions;  // = new HashSet<String>();

    public String toString() {
        return permissions.toString();
    }


    /**
     * Construct a Permission class with no permissions.
     */
    public Permissions() {
        permissions = new HashSet<>();
    }

    public Set<String> getPermissions() {
        return permissions;
    }


    /**
     * Checks if this account has the permission specified.
     *
     * @param perm the permission specified.
     * @return whether the account has the permission specified.
     */
    public boolean hasPerm(String perm) {
        return this.getPermissions().contains("admin") ||
                this.getPermissions().contains(perm);
    }

    // [PERMCLASS].hasPerm("admin")


    /**
     * Attempt to add the permission.
     *
     * @param perm the permission to add. The permission should be camelCase.
     * @return whether adding the permission was successful.
     */
    public boolean addPerm(String perm) {
        if (perm.matches("[A-Za-z\\d]+")) {
            this.permissions.add(perm);
            return true;
        } else return false;
    }

}
