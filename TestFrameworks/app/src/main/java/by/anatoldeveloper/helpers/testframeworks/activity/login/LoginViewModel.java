package by.anatoldeveloper.helpers.testframeworks.activity.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import by.anatoldeveloper.helpers.testframeworks.activity.model.User;
import by.anatoldeveloper.helpers.testframeworks.activity.service.AuthenticationRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Boolean> showProgress;
    private final MutableLiveData<Boolean> isErrorVisible;
    private final MutableLiveData<User> user;
    private final MutableLiveData<String> error;
    private final AuthenticationRepository repository;

    public LoginViewModel(AuthenticationRepository repository) {
        this.repository = repository;
        showProgress = new MutableLiveData<>();
        user = new MutableLiveData<>();
        error = new MutableLiveData<>();
        isErrorVisible = new MutableLiveData<>();
    }

    public void loginClick(String userName, String password) {
        showProgress.setValue(true);
        repository.getUser(userName, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    user -> {
                        showProgress.postValue(false);
                        LoginViewModel.this.user.postValue(user);
                    },
                    throwable -> {
                        showProgress.postValue(false);
                        isErrorVisible.postValue(true);
                        error.postValue("Incorrect userName or password");
                    }
            );
    }

    public void changeValue() {
        isErrorVisible.postValue(false);
    }

    public LiveData<Boolean> showProgress() {
        return showProgress;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> isErrorVisible() {
        return isErrorVisible;
    }

}