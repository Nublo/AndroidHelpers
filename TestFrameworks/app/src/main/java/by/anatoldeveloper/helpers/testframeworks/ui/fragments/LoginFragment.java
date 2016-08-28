package by.anatoldeveloper.helpers.testframeworks.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import by.anatoldeveloper.helpers.testframeworks.R;

/**
 * Created by Nublo on 27.08.2016.
 * Copyright Nublo
 */
public class LoginFragment extends BaseFragment {

    public static final String DEFAULT_LOGIN = "123";
    public static final String DEFAULT_PASSWORD = "123";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView invalidCredentialsTextView;
    private Handler uiHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return createLoginView(inflater, container);
    }

    private View createLoginView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        usernameEditText = (EditText) view.findViewById(R.id.login_username);
        passwordEditText = (EditText) view.findViewById(R.id.login_password);
        invalidCredentialsTextView = (TextView) view.findViewById(R.id.login_invalid_credentials);
        final Button loginButton = (Button) view.findViewById(R.id.login_enter);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invalidCredentialsTextView.setVisibility(View.GONE);
                if (isUserValid(usernameEditText.getText().toString(), passwordEditText.getText().toString())) {
                    startSuccessLoginProcess();
                } else {
                    invalidCredentialsTextView.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

    private void startSuccessLoginProcess() {
        showProgressDialog();
        if (uiHandler == null)
            uiHandler = new Handler(Looper.getMainLooper());
        Thread loginSuccess = new Thread(new Runnable() {
            @Override
            public void run() {
                uiHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        invalidCredentialsTextView.setVisibility(View.VISIBLE);
                        invalidCredentialsTextView.setText("Everything is fine");
                    }
                }, 1500);
            }
        });
        loginSuccess.start();
    }

    private boolean isUserValid(String userName, String password) {
        return DEFAULT_LOGIN.equals(userName) && DEFAULT_PASSWORD.equals(password);
    }

}
