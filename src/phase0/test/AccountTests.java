import entities.Account;
import org.junit.Test;

import java.util.Date;

import static entities.Today.getToday;
import static org.junit.Assert.assertTrue;

public class AccountTests {

    @Test(timeout = 50)
    public void testAccountHistory() {
        Account account = new Account("username", "password");
        account.addNowToAccountHistory();
        Date now = getToday();
        assertTrue("The current time and the time just added to account history are more than 50ms apart!", now.getTime() - Long.parseLong(account.accountHistory().repr()) < 50);
    }
}