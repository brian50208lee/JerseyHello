package com.brian.jerseyhello.resources;

import com.brian.jerseyhello.resources.bean.MessageFilterBean;
import com.brian.jerseyhello.services.MessageService;
import com.brian.jerseyhello.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getMessageFilteredYear(filterBean.getYear());
        }
        if (filterBean.getStart() >=0 && filterBean.getSize() > 0) {
            return messageService.getMessagePaginated(filterBean.getStart(), filterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    public Response createMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.createMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI newUri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(newUri).entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public Message removeMessage(@PathParam("messageId") long messageId) {
        return messageService.removeMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource delegateCommentResource() {
        return new CommentResource();
    }
}
