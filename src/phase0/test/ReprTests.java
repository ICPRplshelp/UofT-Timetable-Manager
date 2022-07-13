import entities.BanStatus;
import entities.DateEntries;
import entities.Permissions;
import org.junit.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ReprTests {

    @Test(timeout = 50)
    public void testBanStatusInverse() {
        String original = "1700000000000";
        BanStatus banStatus = new BanStatus();
        banStatus.setFromRepr(original);
        assertEquals("Using the repr method after the setFromRepr method did not yield the original value! These two methods are not inverses of one another.", original, banStatus.repr());
    }

    @Test(timeout = 50)
    public void testBanStatusRepr() {
        BanStatus banStatus = new BanStatus();
        banStatus.ban(new Date(0L));
        assertEquals("The ban date received from repr is incorrect!", "0", banStatus.repr());
    }

    @Test(timeout = 50)
    public void testDateEntriesInverse() {
        String original = "0:100000000:20000000000";
        DateEntries dateEntries = new DateEntries();
        dateEntries.setFromRepr(original);
        assertEquals("Using the repr method after the setFromRepr method did not yield the original value! These two methods are not inverses of one another.", original, dateEntries.repr());
    }

    @Test(timeout = 50)
    public void testDateEntriesInverse2() {
        String original = "3521:754282:34593568";
        DateEntries dateEntries = new DateEntries();
        dateEntries.setFromRepr(original);
        assertEquals("Using the repr method after the setFromRepr method did not yield the original value! These two methods are not inverses of one another.", original, dateEntries.repr());
    }

    @Test(timeout = 50)
    public void testPermissionsSetFromRepr() {
        String original = "perm1:perm2:perm3";
        Permissions permissions = new Permissions();
        permissions.setFromRepr(original);
        assertEquals("setFromRepr method is incorrect!", new HashSet<>(Arrays.asList("perm1", "perm2", "perm3")) {
        }, permissions.getPermissions());
    }

    @Test(timeout = 50)
    public void testPermissionsSetFromRepr2() {
        String original = "perm1:perm2:perm3:perm4:perm5";
        Permissions permissions = new Permissions();
        permissions.setFromRepr(original);
        assertEquals("setFromRepr method is incorrect!", new HashSet<>(Arrays.asList("perm1", "perm2", "perm3", "perm4", "perm5")) {
        }, permissions.getPermissions());
    }

    @Test(timeout = 50)
    public void testPermissionsRepr() {
        Permissions permissions = new Permissions(Arrays.asList("perm1", "perm2"));
        permissions.repr();
        assertEquals("setFromRepr method is incorrect!", "perm1:perm2", permissions.repr());
    }

    @Test(timeout = 50)
    public void testPermissionsInverse() {
        String original = "perm1:perm2";
        Permissions permissions = new Permissions();
        permissions.setFromRepr(original);
        assertEquals("Using the repr method after the setFromRepr method did not yield the original value! These two methods are not inverses of one another.", original, permissions.repr());
    }
}