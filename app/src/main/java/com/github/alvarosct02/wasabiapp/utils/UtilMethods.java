package com.github.alvarosct02.wasabiapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.github.alvarosct02.wasabiapp.App;

/**
 * Created by rubymobile on 12/20/16.
 */

public class UtilMethods {

    private static Toast toast;
    private static ProgressDialog loadingDialog;

    static {
        toast = Toast.makeText(App.getContext(), "", Toast.LENGTH_SHORT);
    }

    public static void upcomingFeature() {
        toast.setText("Upcoming Feature");
        toast.show();
    }

    public static void showLoadingDialog(Context context) {
        loadingDialog = ProgressDialog.show(context, "", "Loading. Please wait...", true);
    }

    public static void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.hide();
        }
    }
}
