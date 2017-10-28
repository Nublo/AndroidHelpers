package by.anatoldeveloper.helpers.testframeworks.activity.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import by.anatoldeveloper.helpers.testframeworks.activity.login.LoginViewModel;
import by.anatoldeveloper.helpers.testframeworks.activity.service.AuthenticationRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final AuthenticationRepository authenticationRepository;

    public LoginViewModelFactory(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> clazz) {
        if (clazz.isAssignableFrom(LoginViewModel.class))
            return (T) new LoginViewModel(authenticationRepository);
        throw new IllegalArgumentException("Unknown class");
    }

}