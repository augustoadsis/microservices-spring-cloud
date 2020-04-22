package com.microservices.user.mq;

import com.microservices.user.UserDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    public boolean sendMessageProduct(UserDTO payload, UserSource userSource){
        Message<UserDTO> message = MessageBuilder.withPayload(payload).build();
        boolean success = userSource.sendMessage().send(message);
        return success;
    }

}
