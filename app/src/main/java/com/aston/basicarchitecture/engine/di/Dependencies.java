package com.aston.basicarchitecture.engine.di;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dependencies {

    //Again making singleton till DI is implemented
    private static Dependencies single_instance = null;

    private Dependencies() { }

    public static Dependencies getInstance()
    {
        if (single_instance == null) {
            single_instance = new Dependencies();
            single_instance.setup();
        }

        return single_instance;
    }

    //Set Like this till we add Dependency Injection
    private Retrofit retrofit;

    void setup() {
        if(retrofit == null) _setupAPIs();
    }

    void _setupAPIs() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
