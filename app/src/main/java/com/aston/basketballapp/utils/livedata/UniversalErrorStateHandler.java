package com.aston.basketballapp.utils.livedata;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.aston.basketballapp.R;
import com.google.android.material.button.MaterialButton;

public class UniversalErrorStateHandler {


    public static void isLoading(View v) {
        ProgressBar bar = v.findViewById(R.id.universal_progress_bar);
        LinearLayout errorLayout = v.findViewById(R.id.universal_error_state);

        bar.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.INVISIBLE);
    }

    public static void isError(View v) {
        ProgressBar bar = v.findViewById(R.id.universal_progress_bar);
        LinearLayout errorLayout = v.findViewById(R.id.universal_error_state);

        bar.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    public static void isSuccess(View v) {
        ProgressBar bar = v.findViewById(R.id.universal_progress_bar);
        LinearLayout errorLayout = v.findViewById(R.id.universal_error_state);

        bar.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.INVISIBLE);
    }

    public static MaterialButton getRetryButton(View v) {
        return v.findViewById(R.id.universal_retry_button);
    }
}
