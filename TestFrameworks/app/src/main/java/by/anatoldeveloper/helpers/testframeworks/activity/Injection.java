package by.anatoldeveloper.helpers.testframeworks.activity;

import by.anatoldeveloper.helpers.testframeworks.activity.service.AuthenticationRepository;
import by.anatoldeveloper.helpers.testframeworks.activity.service.AuthenticationRepositoryImpl;

public class Injection {

    public static AuthenticationRepository getRepository() {
        return new AuthenticationRepositoryImpl();
    }

}