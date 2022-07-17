package org.example.logincode.interfaceadapters;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main prog = new Main();
        prog.runProgram();

    }

    private void runProgram() {
        Controller controller = new Controller();
        LoginPresenter loginPresenter = new LoginPresenter();
        Scanner scanner = new Scanner(System.in);

        login(controller, loginPresenter, scanner);

        String tLine2;
        tLine2 = controller.loginPresenter.printAndAskPrompt(controller.getCommandList());

        eventLoop(controller, tLine2);

        loginPresenter.exitProgram();
        System.exit(0);
    }

    private void eventLoop(Controller controller, String tLine2) {
        while (!tLine2.equals("exit")) {
            controller.inputParser(tLine2);
            tLine2 = controller.loginPresenter.printAndAskPrompt(controller.getCommandList());
        }
    }

    private void login(Controller controller, LoginPresenter loginPresenter, Scanner scanner) {
        String line;
        while (controller.getLoginState() == Controller.LoginState.LOGGED_OUT) {
            loginPresenter.startView();
            line = scanner.nextLine();
            if (line.equals("exit")) {
                loginPresenter.exitProgram();
                System.exit(0);
            }
            controller.loggedOutMenu(line);
        }
    }
}
