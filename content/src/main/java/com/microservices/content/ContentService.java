package com.microservices.content;

import com.microservices.core.exceptions.ObjectNotFoundException;
import com.microservices.core.user.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Flux<Content> findByTitle(String title) {
        return contentRepository.findByTitle(title);
    }

}
