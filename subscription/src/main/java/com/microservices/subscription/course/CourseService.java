package com.microservices.subscription.course;

import com.microservices.core.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private RestTemplate restTemplate;

    public CourseDTO findById(Long id)  {
        CourseDTO course = restTemplate.getForObject("http://localhost:8080/gateway/course/v1/courses/{id}", CourseDTO.class, id);
        return course;
    }

}
