package com.brian.jerseyhello.core.services;

import com.brian.jerseyhello.core.database.Database;
import com.brian.jerseyhello.core.exception.IllegalDataException;
import com.brian.jerseyhello.core.exception.DataNotFoundException;
import com.brian.jerseyhello.core.data.Message;

import java.util.*;

public class MessageService {
    private Database db;
    private Map<Long, Message> messages;

    public MessageService(Database db) {
        this.db = db;
        this.messages = db.getMessages();
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessageFilteredYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Message message : messages.values()) {
            calendar.setTime(message.getCreated());
            if (calendar.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getMessagePaginated(int start, int size) {
        List<Message> messagesPaginated = new ArrayList<>(messages.values());
        if (start + size > messagesPaginated.size()) {
            return new ArrayList<>();
        }
        return messagesPaginated.subList(start, start + size);
    }

    public Message createMessage(Message message) {
        message.setId(messages.size() + 1);
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public Message getMessage(long id) {
        checkMessageExistOrThrowException(id);
        return messages.get(id);
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            throw new IllegalDataException(String.format("Illegal message id = %d", message.getId()));
        }
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        checkMessageExistOrThrowException(id);
        Message message = messages.get(id);
        messages.put(id, null);
        return message;
    }

    private void checkMessageExistOrThrowException(long id) {
        if (!messages.containsKey(id)) {
            throwMessageNotFoundException(id);
        }
    }

    private void throwMessageNotFoundException(long id) {
        throw new DataNotFoundException(String.format("Message not found, id = %d", id));
    }

}
