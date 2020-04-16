package com.microservices.auth.user;

import com.microservices.core.abstractEntity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    private String name;
    private String description;

}
