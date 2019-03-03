package com.brian.jerseyhello.services;

import com.brian.jerseyhello.database.DummyDatabase;
import com.brian.jerseyhello.model.Comment;
import com.brian.jerseyhello.model.Message;

import java.util.*;

public class CommentService {
    private Map<Long, Message> messages = DummyDatabase.getInstance().getMessages();

    public CommentService() {
    }

    public List<Comment> getAllComments(long messageId) {
        if (!isMessageExist(messageId)) {
            return null;
        }
        Message message = messages.get(messageId);
        return new ArrayList<>(message.getComments().values());
    }


    public Comment getComment(long messageId, long commentId) {
        if (!isMessageExist(messageId)) {
            return null;
        }
        Message message = messages.get(messageId);
        return message.getComments().get(commentId);
    }

    public Comment createComment(long messageId, Comment comment) {
        if (!isMessageExist(messageId)) {
            return null;
        }
        Message message = messages.get(messageId);
        comment.setId(message.getComments().size() + 1);
        comment.setCreated(new Date());
        message.getComments().put(comment.getId(), comment);
        return comment;
    }


    public Comment updateComment(long messageId, Comment comment) {
        if (!isMessageExist(messageId)) {
            return null;
        }
        Message message = messages.get(messageId);
        comment.setCreated(new Date());
        message.getComments().put(comment.getId(), comment);
        return comment;
    }

    public void removeComment(long messageId, long commentId) {
        if (!isMessageExist(messageId) || commentId <= 0) {
            return;
        }
        Message message = messages.get(messageId);
        message.getComments().put(commentId, null);
    }

    private boolean isMessageExist(long messageId) {
        return messages.get(messageId) != null;
    }
}
