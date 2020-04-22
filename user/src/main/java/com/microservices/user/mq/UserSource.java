package com.microservices.user.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

    public interface UserSource {

        @Output("user-channel")
        public MessageChannel sendMessage();

    }
