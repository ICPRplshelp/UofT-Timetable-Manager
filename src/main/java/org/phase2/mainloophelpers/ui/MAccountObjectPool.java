package org.phase2.mainloophelpers.ui;

import org.example.logincode.usecases.StorageManager;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;

/**
 * This is in the controller layer.
 */
public class MAccountObjectPool {

    private final StorageManager sm;
    private final String username;

    public MAccountObjectPool(String username, MAccountLoginValidator malv) {
        this.sm = malv.getStorageManager();
        this.username = username;
    }


}
