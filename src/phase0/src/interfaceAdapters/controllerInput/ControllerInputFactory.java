package interfaceAdapters.controllerInput;

import entities.AccountStorage;
import interfaceAdapters.Presenter;
import useCases.AccountManager;
import useCases.StorageManager;

public class ControllerInputFactory {

    private AccountManager manager;
    private final StorageManager storageManager;
    private final Presenter presenter;

    public ControllerInputFactory(AccountManager manager,
                                  StorageManager storageManager,
                                  Presenter presenter){
        this.manager = manager;
        this.storageManager = storageManager;
        this.presenter = presenter;
    }

    public void setManager(AccountManager manager) {
        this.manager = manager;
    }

    /**
     * Obtain the controller input.
     * @param typeOf the controller input to give me
     *
     * @return the ControllerInput to return
     */
    public ControllerInput getControllerInput(LoggedInState typeOf
                                              ){
        switch (typeOf) {
            case STANDARD -> {
                return new ControllerInputStandard(manager, storageManager, presenter);
            }
            case ADMIN -> {
                return new ControllerInputAdmin(manager, storageManager, presenter);
            }
            default -> {
                return null;
            }
        }

    }

}
