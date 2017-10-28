package by.anatoldeveloper.helpers.testframeworks.activity.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.widget.RxTextView;

import by.anatoldeveloper.helpers.testframeworks.R;
import by.anatoldeveloper.helpers.testframeworks.activity.Injection;
import by.anatoldeveloper.helpers.testframeworks.activity.factory.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private MaterialDialog progressDialog;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView invalidCredentialsTextView;

    private LoginViewModel loginViewModel;

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new MaterialDialog.Builder(this)
                    .title(R.string.progress_waiting_title)
                    .content(R.string.progress_waiting_text)
                    .progress(true, 0)
                    .cancelable(false)
                    .build();
        }
        progressDialog.show();
    }

    protected void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app);
        usernameEditText = findViewById(R.id.login_username);
        passwordEditText = findViewById(R.id.login_password);
        invalidCredentialsTextView = findViewById(R.id.login_invalid_credentials);

        LoginViewModelFactory factory = new LoginViewModelFactory(Injection.getRepository());
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);

        final Button loginButton = findViewById(R.id.login_enter);
        loginButton.setOnClickListener(view ->
                loginViewModel.loginClick(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()));
        loginViewModel.showProgress().observe(this,
                show -> {
                    if (show)
                        showProgressDialog();
                    else
                        closeProgressDialog();
                });
        loginViewModel.getError().observe(this,
                error -> invalidCredentialsTextView.setText(error));
        loginViewModel.isErrorVisible().observe(this,
                visible -> invalidCredentialsTextView.setVisibility(visible ? View.VISIBLE : View.GONE));
        RxTextView.afterTextChangeEvents(usernameEditText)
                .subscribe(next -> loginViewModel.changeValue());
        RxTextView.afterTextChangeEvents(passwordEditText)
                .subscribe(next -> loginViewModel.changeValue());
    }

}