package com.microservices.content;

import com.microservices.core.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/contents")
@Slf4j
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private RestResponse response;

    @GetMapping("/{title}")
    public Flux<Content> findByTitle(@PathVariable("title") String title) {
        return contentService.findByTitle(title);
    }

}
