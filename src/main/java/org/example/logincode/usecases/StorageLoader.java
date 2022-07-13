package org.example.logincode.usecases;

import java.io.*;
import org.example.logincode.entities.AccountStorage;

/**
 * This class lets you load accounts from a CSV
 * and also lets you export account data to a CSV.
 * Only if passwords were hashed...
 *
 */
public class StorageLoader {

    protected AccountStorage accountStorage;

    public StorageLoader(AccountStorage accountStorage) {
        this.accountStorage = accountStorage;
    }

    /**
     * Constructs this class
     * Loads information from ser file
     */

    public StorageLoader(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        accountStorage = (AccountStorage) in.readObject();

        in.close();
        fileIn.close();
    }

    /**
     * Empty constructor for when there's no file.
     */
    public StorageLoader() {
    }


    public void updateAccounts(AccountStorage tempAccountStorage) {
        try {
            FileOutputStream fileOut = new FileOutputStream("accountInformation.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tempAccountStorage);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();

    }
    }
}


