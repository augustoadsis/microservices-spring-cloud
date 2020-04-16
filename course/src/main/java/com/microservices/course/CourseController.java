package com.microservices.course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/course")
@Slf4j
public class CourseController {

    @GetMapping
    public ResponseEntity<Object> list() {
        return new ResponseEntity<Object>("courseService.list(pageable)", HttpStatus.OK);
    }
}
