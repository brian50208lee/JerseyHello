package com.brian.jerseyhello.jersey.Exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        Status status = Response.Status.INTERNAL_SERVER_ERROR;
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), status.getStatusCode(), null);
        return Response.status(status).entity(errorMessage).build();
    }
}
