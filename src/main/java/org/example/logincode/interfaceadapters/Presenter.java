package org.example.logincode.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Presenter {
    Scanner scanner = new Scanner(System.in);
    PresenterPrinter prt = new PresenterPrinter();
}
