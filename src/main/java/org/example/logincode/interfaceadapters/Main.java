package org.example.logincode.interfaceadapters;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main prog = new Main();
        prog.runProgram();

    }

    private void runProgram() {
        Controller controller = new Controller();
        Presenter presenter = new Presenter();
        Scanner scanner = new Scanner(System.in);

        login(controller, presenter, scanner);

        String tLine2;
        tLine2 = controller.presenter.printAndAskPrompt(controller.getCommandList());

        eventLoop(controller, tLine2);

        presenter.exitProgram();
        System.exit(0);
    }

    private void eventLoop(Controller controller, String tLine2) {
        while (!tLine2.equals("exit")) {
            controller.inputParser(tLine2);
            tLine2 = controller.presenter.printAndAskPrompt(controller.getCommandList());
        }
    }

    private void login(Controller controller, Presenter presenter, Scanner scanner) {
        String line;
        while (controller.getLoginState() == Controller.LoginState.LOGGED_OUT) {
            presenter.startView();
            line = scanner.nextLine();
            if (line.equals("exit")) {
                presenter.exitProgram();
                System.exit(0);
            }
            controller.loggedOutMenu(line);
        }
    }
}
