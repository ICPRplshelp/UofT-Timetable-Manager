package slatedForRemoval;

import entities.Account;

/**
 * A class representing a user. A user is supposed to have an account.
 */
public class User {
    public Account currAccount;

    public boolean isLoggedIn() {
        return currAccount != null;
    }
}
