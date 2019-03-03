package com.brian.jerseyhello.database;

import com.brian.jerseyhello.model.Message;
import com.brian.jerseyhello.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DummyDatabase {
    private static DummyDatabase instance = null;

    public static synchronized DummyDatabase getInstance() {
        if (instance == null) {
            instance = new DummyDatabase();
        }
        return instance;
    }

    private Map<Long, Profile> profiles = new HashMap<>();
    private Map<Long, Message> messages = new HashMap<>();

    private DummyDatabase() {
    }

    public Map<Long, Profile> getProfiles() {
        return profiles;
    }

    public Map<Long, Message> getMessages() {
        return messages;
    }
}
