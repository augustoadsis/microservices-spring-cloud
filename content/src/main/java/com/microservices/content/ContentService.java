package com.microservices.content;

import com.microservices.core.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Flux<Content> findAll() {
        return contentRepository.findAll();
    }

    public Flux<Content> findByTitle(String title) {
        return contentRepository.findByTitle(title).switchIfEmpty(Flux.error(new ObjectNotFoundException("Course not found")));
    }

    public Mono<Content> save(Content content) {
        return contentRepository.save(content);
    }

    public Mono<Void> delete(Long id) {
        return contentRepository.deleteById(id);
    }

    public Flux<Content> findByCourse(Long id) {
        return contentRepository.findByCourse(id);
    }
}
