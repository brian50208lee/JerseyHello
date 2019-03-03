package com.brian.jerseyhello.services;

import com.brian.jerseyhello.database.DummyDatabase;
import com.brian.jerseyhello.model.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfileService {
    private Map<String, Profile> profiles = DummyDatabase.getInstance().getProfiles();

    public ProfileService() {
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile createProfile(Profile profile) {
        if (profile.getName().isEmpty() || profiles.containsKey(profile.getName())) {
            return null;
        }
        profile.setId(profiles.size() + 1);
        profile.setCreated(new Date());
        profiles.put(profile.getName(), profile);
        return profile;
    }

    public Profile getProfile(String name) {
        return profiles.get(name);
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getName().isEmpty()) {
            return null;
        }
        profile.setCreated(new Date());
        profiles.put(profile.getName(), profile);
        return profile;
    }

    public void removeProfile(String name) {
        profiles.put(name, null);
    }
}

