package com.brian.jerseyhello.resources;

import com.brian.jerseyhello.model.Profile;
import com.brian.jerseyhello.services.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
    ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }

    @POST
    public Profile createProfile(Profile profile) {
        return profileService.createProfile(profile);
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public void removeProfile(@PathParam("profileName") String profileName) {
        profileService.removeProfile(profileName);
    }
}
