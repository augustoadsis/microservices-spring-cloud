package com.microservices.core.user;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO implements Serializable {

    private Long id;

    private String name;
    private String username;
    private String password;

}
