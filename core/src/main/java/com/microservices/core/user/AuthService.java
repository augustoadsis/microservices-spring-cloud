package com.microservices.core.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    public UserDTO findByToken()  {
        UserDTO user = restTemplate.getForObject("http://localhost:8080/gateway/auth/v1/users/token", UserDTO.class);
        return user;
    }

}
