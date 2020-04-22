package com.microservices.course.mq;

import com.microservices.core.user.UserDTO;
import com.microservices.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Lazy;

@EnableBinding(Sink.class)
@Slf4j
public class UserConsumer {

    @Autowired
    @Lazy
    CourseService courseService;

    @StreamListener(Sink.INPUT)
    public void readMessage(UserDTO user) {
       log.info("Removing courses of author: " + user.toString());
       courseService.deleteByUser(user.getId());
    }

}
