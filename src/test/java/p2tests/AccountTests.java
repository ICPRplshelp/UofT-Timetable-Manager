package p2tests;

import org.example.logincode.controllerspresentersgateways.gateways.StorageLoader;
import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import org.example.logincode.usecases.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AccountTests {

    @Test(timeout = 1200)
    public void testCreateAccountAndLogin() {
        AccountCreator accountCreator = new AccountCreator(StorageManager.getInstance(new StorageLoader()));
        accountCreator.createAccountAndAddToStorage("User", "password");
        assertEquals("password", StorageManager.getInstance(new StorageLoader()).getAccount("User").getPassword());
        AccountLogin accountLogin = new AccountLogin(StorageManager.getInstance(new StorageLoader()));
        assertEquals("User", accountLogin.login("User", "password").getUsername());
        assertEquals(true, accountLogin.validateLogin("User", "password"));
    }

    @Test(timeout = 1200)
    public void testAccountAndAdminManager() {
        AccountCreator accountCreator = new AccountCreator(StorageManager.getInstance(new StorageLoader()));
        accountCreator.createAccountAndAddToStorage("Placeholder", "password");
        Account account = new Account("User", "password");
        AccountManager accountManager = new AccountManager(account);
        accountManager.setPassword("password", "test");
        assertEquals("test", accountManager.getAccount().getPassword());
        accountManager.makeMeAnAdmin();
        AdminAccountManager adminAccountManager = new AdminAccountManager(account, StorageManager.getInstance(new StorageLoader()));
        adminAccountManager.createNewAdminUser("test1", "test2");
        assertEquals(true, StorageManager.getInstance(new StorageLoader()).getAccount("test1").getPermissions().hasPerm("admin"));
        adminAccountManager.banUser("Placeholder", new Date(2022, 12, 31));
        assertEquals(true, StorageManager.getInstance(new StorageLoader()).getAccount("Placeholder").getBanStatus().isBanned());
        adminAccountManager.deleteUser("Placeholder");
        assertEquals(null, StorageManager.getInstance(new StorageLoader()).getAccount("Placeholder"));
    }




}
