package com.microservices.course.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CourseSource.class)
public class CourseSourceImpl {

}
