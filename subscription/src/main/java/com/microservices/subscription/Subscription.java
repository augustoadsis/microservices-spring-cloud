package com.microservices.subscription;

import com.microservices.core.abstractEntity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Subscription extends AbstractEntity {

    private Long user;
    private Long course;

}
