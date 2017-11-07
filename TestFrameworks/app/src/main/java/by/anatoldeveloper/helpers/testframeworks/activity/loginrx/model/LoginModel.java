package by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model;

public abstract class LoginModel {

    private final String userName;
    private final String password;
    private final boolean isLoginEnabled;
    private final boolean isDialogShowing;
    private final Throwable error;

    public LoginModel(String userName, String password, boolean isLoginEnabled, boolean isDialogShowing, Throwable error) {
        this.userName = userName;
        this.password = password;
        this.isLoginEnabled = isLoginEnabled;
        this.isDialogShowing = isDialogShowing;
        this.error = error;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoginEnabled() {
        return isLoginEnabled;
    }

    public boolean isDialogShowing() {
        return isDialogShowing;
    }

    public Throwable getError() {
        return error;
    }

}