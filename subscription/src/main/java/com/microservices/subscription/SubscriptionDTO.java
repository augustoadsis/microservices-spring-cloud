package com.microservices.subscription;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO implements Serializable {

    private Long id;
    private Long user;
    private Long course;

    public SubscriptionDTO(Subscription s) {
        this.id = s.getId();
        this.user = s.getUser();
        this.course = s.getCourse();
    }

}
