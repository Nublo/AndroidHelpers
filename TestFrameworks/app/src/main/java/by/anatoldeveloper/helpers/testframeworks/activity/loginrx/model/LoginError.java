package by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model;

public class LoginError extends LoginModel {
    public LoginError(boolean isLoginEnabled, Throwable e) {
        super("", "", isLoginEnabled, false, e);
    }
}
