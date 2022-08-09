package org.phase2.gui;

import org.example.logincode.controllerspresentersgateways.gateways.StorageLoader;
import org.example.logincode.usecases.IGateway;
import org.example.logincode.usecases.StorageManager;

/**
 * Class meant to save state.
 * Clean architecture layer: Controller
 */
public class SaveStateController {

    private static SaveStateController curInstance;

    /**
     * Grabs the instance of this singleton, where only one
     * should exist at a time.
     * @return the instance, I guess?
     */
    public static SaveStateController getInstance(){
        if(curInstance == null){
            curInstance = new SaveStateController();
        }
        return curInstance;
    }

    private SaveStateController(){
        this.storageLoader = new StorageLoader();
    }

    private final IGateway storageLoader;

    /**
     * Updates the save of all accounts.
     */
    public void updateSave() {
        StorageManager.getInstance(storageLoader).updateSave();

    }
}
