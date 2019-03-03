package com.brian.jerseyhello.core.database;

import com.brian.jerseyhello.core.data.Message;
import com.brian.jerseyhello.core.data.Profile;

import java.util.Map;

public interface DataBase {
    public Map<Long, Message> getMessages();
    public Map<String, Profile> getProfiles();
}
