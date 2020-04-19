package com.microservices.auth.user;

import com.microservices.core.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestResponse response;

    @GetMapping("/token")
    public ResponseEntity<Object> findByToken() {
        return response.searchOk(userService.findByToken());
    }
}
