package org.example.logincode.interfaceadapters;

public class StandardPresenter extends Presenter{


    public String[] passwordChangePrompt() {
        String[] inputs = new String[2];
        inputs[0] = prt.askWithMessage("Old password: ");
        inputs[1] = prt.askWithMessage("New password: ");
        return inputs;
    }

    public void printHistory(String history) {
        prt.println(history);
    }
}
