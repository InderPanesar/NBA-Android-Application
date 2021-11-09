package com.aston.basicarchitecture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private ExampleViewModel exampleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        exampleViewModel = new ViewModelProvider(this).get(ExampleViewModel.class);

        Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                textViewResult.setText(newName);
            }
        };

        exampleViewModel.getList().observe(this, nameObserver);
        
    }
}