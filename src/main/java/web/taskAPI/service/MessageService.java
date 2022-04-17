package web.taskAPI.service;

import web.taskAPI.core.dto.Message;
import web.taskAPI.core.dto.Role;
import web.taskAPI.core.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {

    private static MessageService messageService;

    private Map<User, List<Message>> messageMap;

    private MessageService() {
        messageMap = new HashMap<>();
    }

    public static MessageService getInstance() {
        if (messageService == null) {
            messageService = new MessageService();
        }
        return messageService;
    }

    public void sendMessage(Message message) throws IllegalArgumentException {
        UserRepository userRepository = UserRepository.getInstance();
        User user = userRepository.get(message.getToWhom());
        List<Message> messagesList;
        if (messageMap.containsKey(user)) {
            messagesList = messageMap.getOrDefault(user, new ArrayList<>());
        }else {
            messagesList = new ArrayList<>();
        }
        messagesList.add(message);
        messageMap.put(user, messagesList);
    }

    public List<Message> getMessage(User user){
        List<Message> messages = null;
        if (messageMap.containsKey(user)){
            messages = messageMap.get(user);
        }
        return messages;
    }

}
