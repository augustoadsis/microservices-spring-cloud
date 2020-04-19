package com.microservices.user;

import com.microservices.core.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestResponse response;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                          @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                          @RequestParam(value = "orderBy", defaultValue = "name", required = false) String orderBy,
                                          @RequestParam(value = "filter", defaultValue = "ASC", required = false) String filter,
                                          @RequestParam(value = "search", defaultValue = "", required = false) String param) {
        Page<UserDTO> userDTO = userService.findAll(page, size, orderBy, filter, param);
        return nonNull(userDTO) ? response.searchOk(userDTO) : response.noContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return response.searchOk(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return response.ok();
    }

    @PostMapping("/creator")
    public ResponseEntity<Object> saveCreator(@RequestBody UserDTO userDTO) {
        userService.saveCreator(userDTO);
        return response.ok();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.update(id, userDTO);
        return nonNull(user) ? response.ok(user) : response.noContent();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.delete(id);
        return response.noContent();
    }

}
