package com.brian.jerseyhello.resources;

import com.brian.jerseyhello.model.Profile;
import com.brian.jerseyhello.services.ProfileService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
public class ProfileResource {
    ProfileService profileService = new ProfileService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getMessages() {
        return profileService.getAllProfiles();
    }


}
