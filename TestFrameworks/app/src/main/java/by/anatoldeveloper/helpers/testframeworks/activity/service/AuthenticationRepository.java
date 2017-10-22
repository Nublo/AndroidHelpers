package by.anatoldeveloper.helpers.testframeworks.activity.service;

import by.anatoldeveloper.helpers.testframeworks.activity.model.Offer;
import by.anatoldeveloper.helpers.testframeworks.activity.model.User;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface AuthenticationRepository {

    Single<User> getUser(String userName, String password);
    Single<Offer> makeOffer(@NonNull String userId);

}