package com.microservices.course.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CourseSource {

    @Output("course-channel")
    public MessageChannel sendMessage();

}
