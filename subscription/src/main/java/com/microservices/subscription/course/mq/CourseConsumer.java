package com.microservices.subscription.course.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CourseConsumer {

    String CHANNEL = "course-channel";

    @Input("course-channel")
    SubscribableChannel courseChannel();
}
