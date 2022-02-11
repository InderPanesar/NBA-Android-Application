package com.aston.basketballapp.engine.model;

import java.util.ArrayList;

public class ExampleModelApi {
    private float status;
    private String message;
    private float results;
    ArrayList<String> filters = new ArrayList<String>();
    ArrayList<String> seasons = new ArrayList<String>();

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public float getResults() {
        return results;
    }

    public ArrayList<String> getSeasons() {
        return seasons;
    }


}
