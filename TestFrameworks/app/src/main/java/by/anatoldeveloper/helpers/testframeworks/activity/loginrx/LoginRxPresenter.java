package by.anatoldeveloper.helpers.testframeworks.activity.loginrx;

import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginButtonState;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginError;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginInitModel;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginLoading;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginModel;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginSuccess;
import by.anatoldeveloper.helpers.testframeworks.activity.service.AuthenticationRepository;
import io.reactivex.Observable;

public class LoginRxPresenter {

    private final LoginRxView view;
    private final AuthenticationRepository repository;

    public LoginRxPresenter(LoginRxView view, AuthenticationRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public Observable<LoginModel> model() {
        Observable<LoginModel> buttonState = Observable.combineLatest(
                view.passwordObservable(),
                view.userNameObservable(),
                (password, userName) -> {
                    boolean isLoginEnabled = !password.isEmpty() && !userName.isEmpty();
                    return new LoginButtonState(userName, password, isLoginEnabled);
                }
        );

        Observable<LoginModel> loginGetUser = view.clickObservable()
                .withLatestFrom(buttonState, (click, it) -> it)
                .flatMap(state -> repository.initLogin().toObservable()
                                .flatMap(loginInitParams -> repository.login(state.getUserName(), state.getPassword(), loginInitParams).toObservable()
                                        .map(loginResult -> (LoginModel) new LoginSuccess()))
                                        .onErrorReturn(e -> new LoginError(state.isLoginEnabled(), e))
                                .startWith(new LoginLoading(state.isLoginEnabled()))
                                .onErrorReturn(e -> new LoginError(state.isLoginEnabled(), e)));

        return Observable.<LoginModel>just(new LoginInitModel())
                .mergeWith(buttonState)
                .mergeWith(loginGetUser);
    }

}