package com.microservices.content;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/contents")
@Slf4j
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping(produces = "application/stream+json")
    public Flux<Content> findAll() {
        return contentService.findAll();
    }

    @GetMapping("/{title}")
    public Flux<Content> findByTitle(@PathVariable("title") String title) {
        return contentService.findByTitle(title);
    }

    @GetMapping("/course/{id}")
    public Flux<Content> findByCourse(@PathVariable("id") Long id) {
        return contentService.findByCourse(id);
    }

    @PostMapping
    public Mono<Content> create(@RequestBody Content content) {
        return contentService.save(content);
    }

    @PutMapping
    public Mono<Content> update(@RequestBody Mono<Content> content) {
        return content.flatMap(c -> contentService.save(c));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return contentService.delete(id);
    }

}
