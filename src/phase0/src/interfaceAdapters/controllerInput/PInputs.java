package interfaceAdapters.controllerInput;

public enum PInputs {

    // unused because logging in is managed differently
    REGISTER,
    LOGIN,

    // if your input was otherworldly A.K.A. NULL
    INVALID_INPUT,

    // standard account
    HISTORY,
    SET_PASSWORD,
    ADMIN_VIEW,

    // admin only
    BAN,
    DELETE,
    NEW,
    PROMOTE,
    BACK,

    // EASTER EGG
    SECRET_ADMIN
}
