package by.anatoldeveloper.helpers.testframeworks.activity.service;

import java.util.concurrent.TimeUnit;

import by.anatoldeveloper.helpers.testframeworks.activity.model.Offer;
import by.anatoldeveloper.helpers.testframeworks.activity.model.User;
import io.reactivex.Single;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private static final String DEFAULT = "123";

    @Override
    public Single<User> getUser(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName) && DEFAULT.equalsIgnoreCase(password)) {
            User user = new User("431", "James", "Macconel");
            return Single.just(user)
                    .delay(7, TimeUnit.SECONDS);
        }
        return Single.fromCallable(() -> {
            Thread.sleep(7000);
            throw new RuntimeException();
        });
    }

    @Override
    public Single<Offer> makeOffer(String userId) {
        return null;
    }

}