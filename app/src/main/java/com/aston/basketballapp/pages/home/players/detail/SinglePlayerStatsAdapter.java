package com.aston.basketballapp.pages.home.players.detail;

import java.util.List;

public class SinglePlayerStatsAdapter {
    List<String> topics;
    List<String> attributes;

    SinglePlayerStatsAdapter(List<String> _topics, List<String> _attributes) {
        this.topics =_topics;
        this.attributes = _attributes;
    }

    public List<String> getTopics() {
        return topics;
    }

    public List<String> getAttributes() {
        return attributes;
    }


}
