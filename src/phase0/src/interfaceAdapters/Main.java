package interfaceAdapters;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        Presenter presenter = new Presenter();

        Scanner scanner = new Scanner(System.in);

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

        String tLine2;
        tLine2 = controller.presenter.printAndAskPrompt(controller.getLoggedInState());
        while (!tLine2.equals("exit")) {
            controller.inputParser(tLine2);
            tLine2 = controller.presenter.printAndAskPrompt(controller.getLoggedInState());
        }

        presenter.exitProgram();
        System.exit(0);

    }
}
