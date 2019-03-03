package com.brian.jerseyhello.resources;

import com.brian.jerseyhello.model.Comment;
import com.brian.jerseyhello.model.Profile;
import com.brian.jerseyhello.services.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
    CommentService commentService = new CommentService();


    @GET
    public List<Comment> getMessage(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentService.getComment(messageId, commentId);
    }

    @POST
    public Response createMessage(@PathParam("messageId") long messageId, Comment comment, @Context UriInfo uriInfo) {
        Comment newComment = commentService.createComment(messageId, comment);
        String newId = String.valueOf(newComment.getId());
        URI newUri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(newUri).entity(newComment).build();
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment removeMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentService.removeComment(messageId, commentId);
    }
}

