package com.microservices.subscription.user.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserConsumer {

    String CHANNEL = "user-channel";

    @Input("user-channel")
    SubscribableChannel userChannel();
}
