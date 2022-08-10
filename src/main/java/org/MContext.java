package org;

import org.example.logincode.uiinput.UIInput2;
import org.example.mainloophelpers.controllerspresenters.UICommandList;
import org.example.mainloophelpers.ui.UIObjectPool;
import org.example.objectcreators.uifactories.UIFactory;

public class MContext {

    private final UIFactory uif;
    private final UIObjectPool op;
    private final UICommandList cmdList;
    private String curState;
    private UIInput2 curUI;

    /**
     * Constructs this class.
     *
     * @param uif          UIFactory - an instance of it
     * @param op           UIObjectPool - an instance of it
     * @param cmdList      - the presenter that holds the list of commands
     * @param defaultState - the default state to start at, likely standard
     */
    public MContext(UIFactory uif, UIObjectPool op, UICommandList cmdList, String defaultState) {
        this.uif = uif;
        this.op = op;
        this.cmdList = cmdList;
        setState(defaultState);
        if (this.curUI == null) throw new RuntimeException("The default UI input does not exist");
    }

    /**
     * Prints the global set of commands
     * to the screen (mainly switches)
     */
    public void printGlobalCommands() {
        cmdList.printGlobal();
    }

    /**
     * Prints the current set of commands to the
     * screen that are not global, based on the
     * current UIInput
     */
    public void printCommandList() {
        curUI.printCommandList();
    }

    /**
     * Sets the current state
     *
     * @param state the state to set it as, being a string,
     *              not case-sensitive and removes leading
     *              and trailing space and newlines
     * @return whether a switch was successful
     */
    public boolean setState(String state) {
        switch (state.toLowerCase().trim()) {
            case "standard", "std" -> {
                this.curUI = op.getStandard();
                curState = "standard";
            }
            case "admin", "adminview", "ad", "adm" -> {
                this.curUI = op.getAdmin();
                curState = "admin";
            }
            case "student", "timetable", "ttb", "stu", "stud" -> {
                this.curUI = op.getStudent();
                curState = "student";
            }
            case "search", "coursesearch", "sea", "sh", "se" -> {
                this.curUI = op.getSearch();
                curState = "search";
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    /**
     * Runs a command, based on the current UI mode.
     * Note that this has nothing to do with changing the current
     * state.
     *
     * @param cmd the command string.
     */
    public void input(String cmd) {
        curUI.inputParser(cmd);
    }

}
