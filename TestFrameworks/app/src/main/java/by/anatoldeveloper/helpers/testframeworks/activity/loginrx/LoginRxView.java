package by.anatoldeveloper.helpers.testframeworks.activity.loginrx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import by.anatoldeveloper.helpers.testframeworks.R;
import by.anatoldeveloper.helpers.testframeworks.activity.Injection;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginModel;
import by.anatoldeveloper.helpers.testframeworks.activity.loginrx.model.LoginSuccess;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginRxView extends AppCompatActivity {

    private MaterialDialog progressDialog;
    private TextView incorrectCredentials;
    private Button loginButton;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Disposable disposable;

    private LoginRxPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app);

        userNameEditText = findViewById(R.id.login_username);
        passwordEditText = findViewById(R.id.login_password);
        incorrectCredentials = findViewById(R.id.login_invalid_credentials);
        loginButton = findViewById(R.id.login_enter);
        presenter = new LoginRxPresenter(this, Injection.getRepository());
    }

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
    protected void onStart() {
        super.onStart();
        disposable = presenter.model()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::render);
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.dispose();
    }

    public Observable<String> userNameObservable() {
        return RxTextView.textChanges(userNameEditText)
                .map(CharSequence::toString);
    }

    public Observable<String> passwordObservable() {
        return RxTextView.textChanges(passwordEditText)
                .map(CharSequence::toString);
    }

    public Observable<Boolean> clickObservable() {
        return RxView.clicks(loginButton)
                .map(it -> true);
    }

    private void render(LoginModel model) {
        loginButton.setEnabled(model.isLoginEnabled());
        if (model.isDialogShowing())
            showProgressDialog();
        else
            closeProgressDialog();
        if (model.getError() != null) {
            incorrectCredentials.setVisibility(View.VISIBLE);
            incorrectCredentials.setText(R.string.error_credentials);
        } else {
            incorrectCredentials.setVisibility(View.GONE);
        }

        if (model instanceof LoginSuccess) {
            // redirect to other page
        }
    }

}