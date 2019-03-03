package com.brian.jerseyhello.services;

import com.brian.jerseyhello.database.DummyDatabase;
import com.brian.jerseyhello.model.Message;

import java.util.*;

public class MessageService {
    private Map<Long, Message> messages = DummyDatabase.getInstance().getMessages();

    public MessageService() {
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
