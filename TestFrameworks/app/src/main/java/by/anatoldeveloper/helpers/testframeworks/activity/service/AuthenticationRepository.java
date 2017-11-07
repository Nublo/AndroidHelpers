package by.anatoldeveloper.helpers.testframeworks.activity.service;

import by.anatoldeveloper.helpers.testframeworks.activity.model.LoginInitParams;
import by.anatoldeveloper.helpers.testframeworks.activity.model.User;
import io.reactivex.Single;

public interface AuthenticationRepository {

    Single<User> getUser(String userName, String password);

    Single<LoginInitParams> initLogin();
    Single<User> login(String userName, String password, LoginInitParams params);

}