package com.microservices.user;

import com.microservices.core.abstractEntity.AbstractEntity;
import com.microservices.user.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends AbstractEntity {

    String name;

    @Column(unique = true)
    String username;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    String password;

}


