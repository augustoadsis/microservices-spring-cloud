package com.microservices.content;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

interface ContentRepository extends ReactiveCrudRepository<Content, Long> {

    @Query("SELECT * FROM content WHERE title like :_title")
    Flux<Content> findByTitle(@Param("_title") String title);
}
