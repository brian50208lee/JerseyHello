package com.brian.jerseyhello.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/inject")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
    @GET
    @Path("/annotation")
    public String getParmUsingAnnotation(@MatrixParam("matrixParm") String matrixParm,
                       @HeaderParam("headerParm") String headerParm,
                       @CookieParam("cookieParm") String cookieParm) {
        return "matrixParm " + matrixParm + " headerParm " + headerParm + " cookieParm " + cookieParm;
    }

    @GET
    @Path("/context")
    public String getParmUsingContex(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        String absPath = uriInfo.getAbsolutePath().toString();
        String cookies = httpHeaders.getCookies().toString();
        return "absPath: " + absPath + " cookies: " + cookies;
    }
}
