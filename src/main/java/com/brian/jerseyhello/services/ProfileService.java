package com.brian.jerseyhello.services;

import com.brian.jerseyhello.database.DummyDatabase;
import com.brian.jerseyhello.model.Message;
import com.brian.jerseyhello.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {
    Map<Long, Profile> profiles = DummyDatabase.getInstance().getProfiles();


    public ProfileService() {
        profiles.put(1L, new Profile(1L, "Brian"));
        profiles.put(2L, new Profile(2L, "Tony"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }
}
