package org.example.logincode.entities;

import java.io.Serializable;
import java.util.*;

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
public class Permissions implements ReprAble, Serializable {
    // superclasses may access permissions - meaning only in this package.

    public static final String adminStr = "admin";
    private final Set<String> permissions;  // = new HashSet<String>();

    public String toString() {
        return permissions.toString();
    }

    public String repr() {
        StringBuilder sb = new StringBuilder();
        this.permissions.forEach(indvPerm -> sb.append(indvPerm).append(":"));
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void setFromRepr(String reprString) {
        permissions.clear();
        String[] perms = reprString.split(":");

        permissions.addAll(Arrays.asList(perms));
    }

    /**
     * Constructs this class, where the permissions are in an
     * array of Strings.
     *
     * @param arrayOfPerms an array of permissions to add.
     */
    public Permissions(String[] arrayOfPerms) {
        permissions = new HashSet<>(List.of(arrayOfPerms));
    }

    /**
     * Constructs this class, where the permissions are in a
     * collection of Strings.
     *
     * @param arrayOfPerms an array of permissions to add.
     */
    public Permissions(Collection<String> arrayOfPerms) {
        permissions = new HashSet<>(arrayOfPerms);
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
     * Checks if this account has ALL the permissions in perms.
     *
     * @param perms an array of permission names to check.
     * @return whether the account has all the permissions.
     */
    public boolean hasPerm(Collection<String> perms) {
        // I miss Python comprehensions

        if (this.getPermissions().contains(adminStr)) {
            return true;
        }
        for (String perm : perms) {
            if (!this.getPermissions().contains(perm)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if this account has ALL the permissions in perms.
     *
     * @param perms a collection of permission names to check.
     * @return whether the account has all the permissions.
     */
    public boolean hasPerm(String[] perms) {
        // I miss Python comprehensions
        for (String perm : perms) {
            if (!this.getPermissions().contains(perm)) {
                return false;
            }
        }
        return true;
    }

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

    public void removePerm(String perm) {
        this.permissions.remove(perm);
    }

}
