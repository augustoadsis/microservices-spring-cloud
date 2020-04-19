package com.microservices.subscription;

import com.microservices.core.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/v1/subscriptions")
@Slf4j
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private RestResponse response;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                          @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                          @RequestParam(value = "orderBy", defaultValue = "course", required = false) String orderBy,
                                          @RequestParam(value = "filter", defaultValue = "ASC", required = false) String filter,
                                          @RequestParam(value = "search", defaultValue = "", required = false) String param) {
        Page<SubscriptionDTO> subscriptions = subscriptionService.findAll(page, size, orderBy, filter, param);
        return nonNull(subscriptions) ? response.searchOk(subscriptions) : response.noContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return response.searchOk(subscriptionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.save(subscriptionDTO);
        return response.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        subscriptionService.delete(id);
        return response.noContent();
    }

}
