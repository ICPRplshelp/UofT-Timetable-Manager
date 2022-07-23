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

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var pr = new PresenterPrinter();
        var st = pr.askWithMessage("TYPE SOMETHING");
        pr.println(st);
    }

    /**
     * Prints the message and runs ask().
     * <p>
     * ask() will return the next line the user types.
     * When this method is run, program execution stops.
     *
     * @param message the message to print.
     * @return what the user typed.
     */
    public String askWithMessage(String message) {
        System.out.println(message);
        return ask();
    }

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
     * Prints the message to the standard output.
     * This method name is shorter.
     *
     * @param message the message to print.
     */
    public void p(String message) {
        System.out.println(message);
    }
}
