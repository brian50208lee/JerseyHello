package com.brian.jerseyhello.database;

import com.brian.jerseyhello.core.data.Message;
import com.brian.jerseyhello.core.data.Profile;
import com.brian.jerseyhello.core.database.Database;

import java.util.HashMap;
import java.util.Map;

public class DummyDatabase implements Database {
    private static DummyDatabase instance = null;

    public static synchronized DummyDatabase getInstance() {
        if (instance == null) {
            instance = new DummyDatabase();
        }
        return instance;
    }

    private Map<Long, Message> messages = new HashMap<>();
    private Map<String, Profile> profiles = new HashMap<>();

    private DummyDatabase() {
        messages.put(1L, new Message(1L, "Brian", "Hi"));
        messages.put(2L, new Message(2L, "Tony", "Hello"));
        profiles.put("Brian", new Profile(1L, "Brian"));
        profiles.put("Tony", new Profile(2L, "Tony"));
    }

    public Map<Long, Message> getMessages() {
        return messages;
    }


    public Map<String, Profile> getProfiles() {
        return profiles;
    }
}
