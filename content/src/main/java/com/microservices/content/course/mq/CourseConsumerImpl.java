package com.microservices.content.course.mq;

import com.microservices.content.ContentService;
import com.microservices.content.course.CourseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Lazy;

@EnableBinding(CourseConsumer.class)
@Slf4j
public class CourseConsumerImpl {

    @Autowired
    @Lazy
    ContentService contentService;

    @StreamListener(target = CourseConsumer.CHANNEL)
    public void readMessage(CourseDTO course) {
       log.info("Removing contents of course: " + course.toString());
       contentService.deleteByCourse(course.getId());
    }

}
