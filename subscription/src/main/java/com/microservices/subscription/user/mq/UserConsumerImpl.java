package com.microservices.subscription.user.mq;

import com.microservices.core.user.UserDTO;
import com.microservices.subscription.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Lazy;

@EnableBinding(UserConsumer.class)
@Slf4j
public class UserConsumerImpl {

    @Autowired
    @Lazy
    SubscriptionService subscriptionService;

    @StreamListener(target = UserConsumer.CHANNEL)
    public void readMessage(UserDTO user) {
        log.info("Removing subscriptions of user: " + user.toString());
        subscriptionService.deleteByUser(user.getId());
    }

}
