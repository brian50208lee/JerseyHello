package com.brian.jerseyhello.resources;

import com.brian.jerseyhello.model.Message;
import com.brian.jerseyhello.model.Profile;
import com.brian.jerseyhello.services.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
    public Response createProfile(Profile profile, @Context UriInfo uriInfo) {
        Profile newProfile = profileService.createProfile(profile);
        String newName = String.valueOf(newProfile.getName());
        URI newUri = uriInfo.getAbsolutePathBuilder().path(newName).build();
        return Response.created(newUri).entity(newProfile).build();
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public Profile removeProfile(@PathParam("profileName") String profileName) {
        return profileService.removeProfile(profileName);
    }
}
