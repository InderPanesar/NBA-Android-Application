package com.aston.basicarchitecture.pages.home.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aston.basicarchitecture.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class landingPage extends Fragment {

    private TextView textViewResult;
    private ExampleViewModel exampleViewModel;

    public landingPage() { }




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
                //Navigation.findNavController(view).navigate(R.id.action_landingPage_to_mainpage2);
            }
        });

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