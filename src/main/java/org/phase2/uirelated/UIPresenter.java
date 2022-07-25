package org.phase2.uirelated;

public class UIPresenter {

    public void successOrFailState(boolean state){
        if(state){
            System.out.println("Action successful.");
        } else {
            System.out.println("Action failed.");
        }
    }

}
