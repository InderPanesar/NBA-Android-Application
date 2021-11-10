package com.aston.basicarchitecture;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link landingPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class landingPage extends Fragment {

    private TextView textViewResult;
    private ExampleViewModel exampleViewModel;

    public landingPage() { }

    public static landingPage newInstance(String param1, String param2) {
        landingPage fragment = new landingPage();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_landing_page, container, false);

        //Get Text Area
        textViewResult = (TextView) v.findViewById(R.id.textView1);

        //When Clicked Move to next fragment.
        textViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_landingPage_to_mainpage2);
            }
        });

        //Get Activities and set Back Button - NOTE: NEEDS TO BE DONE FOR EACH FRAGMENT!
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        //Set the ViewModel
        exampleViewModel = new ViewModelProvider(getActivity()).get(ExampleViewModel.class);

        //Observer
        Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                textViewResult.setText(newName);
            }
        };

        //Set TextView with a list of api requests.
        exampleViewModel.getList().observe(getViewLifecycleOwner(), nameObserver);

        return v;
    }
}