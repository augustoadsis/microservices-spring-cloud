package com.microservices.subscription.course.mq;

import com.microservices.subscription.SubscriptionService;
import com.microservices.subscription.course.CourseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Lazy;

@EnableBinding(CourseConsumer.class)
@Slf4j
public class CourseConsumerImpl {

    @Autowired
    @Lazy
    SubscriptionService subscriptionService;

    @StreamListener(target = CourseConsumer.CHANNEL)
    public void readMessage(CourseDTO course) {
       log.info("Removing subscriptions of course: " + course.toString());
       subscriptionService.deleteByCourse(course.getId());
    }

}
