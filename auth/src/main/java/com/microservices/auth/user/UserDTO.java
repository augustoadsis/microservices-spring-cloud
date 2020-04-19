package com.microservices.auth.user;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String name;
    private String username;
    private String password;

    public UserDTO(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.name = u.getName();
    }
}
