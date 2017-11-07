package by.anatoldeveloper.helpers.testframeworks.activity.service;

import java.util.concurrent.TimeUnit;
import by.anatoldeveloper.helpers.testframeworks.activity.model.LoginInitParams;
import by.anatoldeveloper.helpers.testframeworks.activity.model.User;
import io.reactivex.Single;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private static final String DEFAULT = "123";

    @Override
    public Single<User> getUser(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName) && DEFAULT.equalsIgnoreCase(password)) {
            User user = new User("431", "James", "Macconel");
            return Single.just(user)
                    .delay(5, TimeUnit.SECONDS);
        }
        return Single.fromCallable(() -> {
            Thread.sleep(5000);
            throw new RuntimeException();
        });
    }

    @Override
    public Single<LoginInitParams> initLogin() {
        return Single.just(new LoginInitParams("48a0hdpoc01"))
                .delay(5, TimeUnit.SECONDS);
    }

    @Override
    public Single<User> login(String userName, String password, LoginInitParams params) {
        return getUser(userName, password);
    }

}