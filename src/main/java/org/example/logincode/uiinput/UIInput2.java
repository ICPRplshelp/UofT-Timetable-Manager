package org.example.logincode.uiinput;

import org.example.PresenterPrinter;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;

public abstract class UIInput2 {
    private final PresenterPrinter prt;
    protected final UICommandList cmdList;

    public UIInput2(PresenterPrinter prt,
                    UICommandList cmdList) {
        this.prt = prt;
        this.cmdList = cmdList;
    }

    /**
     * Takes an input.
     *
     * @param input the input.
     */
    public abstract void inputParser(String input);

    /**
     * Gets the presenter printer instance.
     *
     * @return the current presenter printer.
     */
    protected PresenterPrinter getPrt() {
        return prt;
    }

    /**
     * Prints the command list.
     */
    public abstract void printCommandList();
}
