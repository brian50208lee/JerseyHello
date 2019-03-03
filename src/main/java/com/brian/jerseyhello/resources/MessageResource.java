package com.brian.jerseyhello.resources;

import com.brian.jerseyhello.resources.bean.MessageFilterBean;
import com.brian.jerseyhello.services.MessageService;
import com.brian.jerseyhello.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public Message createMessage(Message message) {
        return messageService.createMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void removeMessage(@PathParam("messageId") long messageId) {
        messageService.removeMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource delegateCommentResource() {
        return new CommentResource();
    }
}
