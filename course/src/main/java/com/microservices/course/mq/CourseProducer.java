package com.microservices.course.mq;

import com.microservices.course.CourseDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CourseProducer {

    public boolean sendMessageCourse(CourseDTO payload, CourseSource courseSource){
        Message<CourseDTO> message = MessageBuilder.withPayload(payload).build();
        boolean success = courseSource.sendMessage().send(message);
        return success;
    }

}
