package com.brian.jerseyhello.services;

import com.brian.jerseyhello.database.DummyDatabase;
import com.brian.jerseyhello.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MessageService {
    private Map<Long, Message> messages = DummyDatabase.getInstance().getMessages();

    public MessageService() {
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public Message createMessage(Message message) {
        message.setId(messages.size() + 1);
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        message.setCreated(new Date());
        messages.put(message.getId(), message);
        return message;
    }

    public void removeMessage(long id) {
        messages.put(id, null);
    }
}
