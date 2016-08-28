package by.anatoldeveloper.helpers.testframeworks.ui.fragments;

import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

import by.anatoldeveloper.helpers.testframeworks.R;

/**
 * Created by Nublo on 28.08.2016.
 * Copyright Nublo
 */
public class BaseFragment extends Fragment {

    private MaterialDialog progressDialog;

    protected void showProgressDialog() {
        if (progressDialog == null && getActivity() != null) {
            progressDialog = new MaterialDialog.Builder(getActivity())
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

}