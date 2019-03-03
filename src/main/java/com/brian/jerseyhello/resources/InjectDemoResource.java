package com.brian.jerseyhello.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("inject")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
    @GET
    public String test(@MatrixParam("matrixParm") String matrixParm,
                       @HeaderParam("headerParm") String headerParm,
                       @CookieParam("cookieParm") String cookieParm) {
        return "matrixParm " + matrixParm + " headerParm " + headerParm + " cookieParm " + cookieParm;
    }
}
