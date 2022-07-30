package org.example.logincode.usecases;

import org.example.logincode.entities.AccountStorage;

import java.io.IOException;

public interface IGateway {
    void updateAccounts(AccountStorage tempAccountStorage);

    AccountStorage attemptLoad(String filepath) throws IOException, ClassNotFoundException;
}
