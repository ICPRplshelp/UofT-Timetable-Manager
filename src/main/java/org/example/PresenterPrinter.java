package org.example;

import java.util.Scanner;

/**
 * This class replaces all System.out.println(...) instances in
 * all other presenters.
 * <p>
 * This class is the only class in this project that is allowed
 * to have println statements, other than the main methods
 * that test code.
 * <p>
 * The purpose of this class is to allow us to switch how
 * messages are printed to the user in the future by
 * merely swapping out this class.
 */
public class PresenterPrinter {

    final Scanner scanner = new Scanner(System.in);


    /**
     * This method will return the next line the user types.
     * When this method is run, program execution stops.
     */
    public String ask() {
        return scanner.nextLine();
    }

    /**
     * Prints the message to the standard output.
     *
     * @param message the message to print.
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints out an iterable to the screen split by newlines
     *
     * @param messages the iterable
     */
    public void printIterable(Iterable<String> messages) {
        messages.forEach(System.out::println);
    }

    public void genericSuccessOrFail(boolean state) {
        if (state) System.out.println("Action successful.");
        else
            System.out.println("Incorrect command syntax or action failed. Check the command description for cause of failure.");
    }

    /**
     * Prints a generic failed message.
     */
    public void failInvalidCommand() {
        System.out.println("This isn't a command in the current view mode.");
    }

}
