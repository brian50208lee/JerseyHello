package com.brian.jerseyhello.services;

import com.brian.jerseyhello.Exception.ResourceNotFoundException;
import com.brian.jerseyhello.database.DummyDatabase;
import com.brian.jerseyhello.model.Comment;
import com.brian.jerseyhello.model.Message;

import java.util.*;

public class CommentService {
    private Map<Long, Message> messages = DummyDatabase.getInstance().getMessages();

    public CommentService() {
    }

    public List<Comment> getAllComments(long messageId) {
        checkMessageExistOrThrowException(messageId);
        Message message = messages.get(messageId);
        return new ArrayList<>(message.getComments().values());
    }


    public Comment getComment(long messageId, long commentId) {
        checkCommentExistOrThrowException(messageId, commentId);
        Message message = messages.get(messageId);
        return message.getComments().get(commentId);
    }

    public Comment createComment(long messageId, Comment comment) {
        checkMessageExistOrThrowException(messageId);
        Message message = messages.get(messageId);
        comment.setId(message.getComments().size() + 1);
        comment.setCreated(new Date());
        message.getComments().put(comment.getId(), comment);
        return comment;
    }


    public Comment updateComment(long messageId, Comment comment) {
        checkMessageExistOrThrowException(messageId);
        Message message = messages.get(messageId);
        comment.setCreated(new Date());
        message.getComments().put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
        checkCommentExistOrThrowException(messageId, commentId);
        Message message = messages.get(messageId);
        Comment comment = message.getComments().get(commentId);
        message.getComments().put(commentId, null);
        return comment;
    }

    private void checkMessageExistOrThrowException(long messageId) {
        if (!messages.containsKey(messageId)) {
            throwMessageNotFoundException(messageId);
        }
    }

    private void checkCommentExistOrThrowException(long messageId, long commentId) {
        checkMessageExistOrThrowException(messageId);
        if (!messages.get(messageId).getComments().containsKey(commentId)) {
            throwCommentNotFoundException(commentId);
        }
    }

    private void throwMessageNotFoundException(long messageId) {
        throw new ResourceNotFoundException(String.format("Message not found, messageId = %d", messageId));
    }

    private void throwCommentNotFoundException(long commentId) {
        throw new ResourceNotFoundException(String.format("Comment not found, commentId = %d", commentId));
    }
}
