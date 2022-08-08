package org.example.logincode.controllerspresentersgateways.gateways;

import org.example.logincode.entities.AccountStorage;
import org.example.logincode.usecases.IGateway;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class lets you load accounts from a CSV
 * and also lets you export account data to a CSV.
 * Only if passwords were hashed...
 */
public class StorageLoader implements IGateway {
    private static final Logger LOGGER = Logger.getLogger(StorageLoader.class.getName());
    private static final boolean disable = false;
    protected AccountStorage accountStorage;



    /**
     * Empty constructor for when there's no file.
     */
    public StorageLoader() {
    }

    public AccountStorage attemptLoad(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        accountStorage = (AccountStorage) in.readObject();

        in.close();
        fileIn.close();

        return accountStorage;
    }


    /**
     * Attempts to update the ser storage.
     * @param tempAccountStorage the temporary account storage
     */
    public void updateAccounts(AccountStorage tempAccountStorage) {

        if (disable) return;
        try {
            FileOutputStream fileOut = new FileOutputStream("accountInformation.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tempAccountStorage);
            out.close();
            fileOut.close();
        } catch (NotSerializableException nse) {
            LOGGER.log(Level.WARNING, "Couldn't save anything? Did you change the serialization format?");
        } catch (IOException i) {
            i.printStackTrace();
            LOGGER.log(Level.WARNING, "Couldn't save anything? Blame your computer.");

        }
    }

}


