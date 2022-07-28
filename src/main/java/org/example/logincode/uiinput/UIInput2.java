package org.example.logincode.uiinput;

import org.example.PresenterPrinter;

public abstract class UIInput2 {
    private final PresenterPrinter prt;

    public UIInput2(PresenterPrinter prt){
        this.prt = prt;
    }

    /**
     * Takes an input.
     * @param input the input.
     */
    public abstract void inputParser(String input);

    protected PresenterPrinter getPrt() {
        return prt;
    }
}
