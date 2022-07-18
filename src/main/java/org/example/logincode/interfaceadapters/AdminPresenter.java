package org.example.logincode.interfaceadapters;

import org.example.PresenterPrinter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class AdminPresenter extends StandardPresenter{
    /**
     * Forces the user to enter the credentials. Return their input.
     *
     * @return A string array: [username, password].
     */


    public String enterUsername() {
        prt.println("Enter username of target user: ");
        return scanner.nextLine();
    }

    public Date enterDate() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        prt.println("Enter date in the format dd/MM/yyyy: ");
        String input = prt.askWithMessage("Enter date in the format dd/MM/yyyy: ");
        return dateFormatter.parse(input);
    }
    public void deleteUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully deleted " + username);
        } else {
            prt.println("You don't have permission, the user does not exist, the user has perms that prevents it from being deleted, or you tried to delete yourself.");
        }
    }

    public void createNewAdminConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully created a new Admin " + username);
        } else {
            prt.println("You do not have permission or the user already exists");
        }
    }

    public void promoteUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully promoted " + username);
        } else {
            prt.println("You do not have permission or the user does not exist");
        }
    }

    public void banUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully banned " + username);
        } else {
            prt.println("You do not have permission, the user does not exist, you tried to ban an admin, or you tried to ban yourself.");
        }
    }
}
