package com.microservices.course;

import com.microservices.core.response.RestResponse;
import com.microservices.course.content.ContentDTO;
import com.microservices.course.content.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/v1/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private RestResponse response;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                          @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                          @RequestParam(value = "orderBy", defaultValue = "title", required = false) String orderBy,
                                          @RequestParam(value = "filter", defaultValue = "ASC", required = false) String filter,
                                          @RequestParam(value = "search", defaultValue = "", required = false) String param) {
        Page<CourseDTO> courseDTO = courseService.findAll(page, size, orderBy, filter, param);
        return nonNull(courseDTO) ? response.searchOk(courseDTO) : response.noContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return response.searchOk(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CourseDTO courseDTO) {
        courseService.save(courseDTO);
        return response.ok();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.update(id, courseDTO);
        return nonNull(course) ? response.ok(course) : response.noContent();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        courseService.delete(id);
        return response.noContent();
    }

    @GetMapping(path = "/content/{id}", produces = "application/stream+json")
    public Flux<ContentDTO> findContentByCourse(@PathVariable Long id) {
        return contentService.getContent(id);
    }

}
