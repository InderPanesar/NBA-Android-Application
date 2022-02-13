package com.aston.basketballapp.pages.home.players.detail;

import java.util.List;

//Create an adapter to return data in SinglePlayerStatsModel
public class SinglePlayerStatsAdapter {
    //Topics - Headers for table
    List<String> topics;
    //Attributes - Data for the table for each topic.
    List<String> attributes;

    SinglePlayerStatsAdapter(List<String> _topics, List<String> _attributes) {
        this.topics =_topics;
        this.attributes = _attributes;
    }
}
