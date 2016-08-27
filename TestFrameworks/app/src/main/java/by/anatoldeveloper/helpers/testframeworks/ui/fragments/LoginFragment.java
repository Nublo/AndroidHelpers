package by.anatoldeveloper.helpers.testframeworks.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.anatoldeveloper.helpers.testframeworks.R;

/**
 * Created by Nublo on 27.08.2016.
 * Copyright Nublo
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return createLoginView(inflater, container);
    }

    private View createLoginView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
