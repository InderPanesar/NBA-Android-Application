package com.aston.basketballapp.utils.livedata;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.aston.basketballapp.R;
import com.google.android.material.button.MaterialButton;

//Creating a Handler for a universal error layout handler ("loading_error_states.xml")
public class UniversalErrorStateHandler {

    //Make progressbar visible when loading
    public static void isLoading(View v) {
        ProgressBar bar = v.findViewById(R.id.universal_progress_bar);
        LinearLayout errorLayout = v.findViewById(R.id.universal_error_state);

        bar.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.INVISIBLE);
    }

    //Make error message visible when in error
    public static void isError(View v) {
        ProgressBar bar = v.findViewById(R.id.universal_progress_bar);
        LinearLayout errorLayout = v.findViewById(R.id.universal_error_state);

        bar.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    //Make don't show progress bar or error message successful
    public static void isSuccess(View v) {
        ProgressBar bar = v.findViewById(R.id.universal_progress_bar);
        LinearLayout errorLayout = v.findViewById(R.id.universal_error_state);

        bar.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.INVISIBLE);
    }

    //Get Retry Button for the Error Message to retry API request
    public static MaterialButton getRetryButton(View v) {
        return v.findViewById(R.id.universal_retry_button);
    }
}
