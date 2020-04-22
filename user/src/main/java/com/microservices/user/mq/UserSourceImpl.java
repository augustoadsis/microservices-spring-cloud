package com.microservices.user.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(UserSource.class)
public class UserSourceImpl {

}
