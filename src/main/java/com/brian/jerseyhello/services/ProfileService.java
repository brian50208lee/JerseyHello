package com.brian.jerseyhello.services;

import com.brian.jerseyhello.Exception.IllegalResourceException;
import com.brian.jerseyhello.Exception.ResourceAlreadyExistException;
import com.brian.jerseyhello.Exception.ResourceNotFoundException;
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
        if (profile.getName().isEmpty()) {
            throw new IllegalResourceException("Illegal profile name. empty string");
        }
        if (profiles.containsKey(profile.getName())) {
            throw new ResourceAlreadyExistException("Profile name already exist");
        }
        profile.setId(profiles.size() + 1);
        profile.setCreated(new Date());
        profiles.put(profile.getName(), profile);
        return profile;
    }

    public Profile getProfile(String name) {
        checkProfileExistOrThrowException(name);
        return profiles.get(name);
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getName().isEmpty()) {
            throw new IllegalResourceException("Illegal profile name. empty string");
        }
        profile.setCreated(new Date());
        profiles.put(profile.getName(), profile);
        return profile;
    }

    public Profile removeProfile(String name) {
        checkProfileExistOrThrowException(name);
        Profile profile = profiles.get(name);
        profiles.put(name, null);
        return profile;
    }


    private void checkProfileExistOrThrowException(String name) {
        if (!profiles.containsKey(name)) {
            throwProfileNotFoundException(name);
        }
    }

    private void throwProfileNotFoundException(String name) {
        throw new ResourceNotFoundException(String.format("Profile not found, name = %s", name));
    }

}

