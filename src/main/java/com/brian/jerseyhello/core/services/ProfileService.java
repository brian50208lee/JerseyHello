package com.brian.jerseyhello.core.services;

import com.brian.jerseyhello.core.database.DataBase;
import com.brian.jerseyhello.core.exception.IllegalDataException;
import com.brian.jerseyhello.core.exception.DataAlreadyExistException;
import com.brian.jerseyhello.core.exception.DataNotFoundException;
import com.brian.jerseyhello.core.data.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfileService {
    private DataBase db;
    private Map<String, Profile> profiles;

    public ProfileService(DataBase db) {
        this.profiles  = db.getProfiles();
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile createProfile(Profile profile) {
        if (profile.getName().isEmpty()) {
            throw new IllegalDataException("Illegal profile name. empty string");
        }
        if (profiles.containsKey(profile.getName())) {
            throw new DataAlreadyExistException("Profile name already exist");
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
            throw new IllegalDataException("Illegal profile name. empty string");
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
        throw new DataNotFoundException(String.format("Profile not found, name = %s", name));
    }

}

