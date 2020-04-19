package com.microservices.course;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO implements Serializable {

    private Long id;
    private Long user;

    private String title;
    private String description;
    private BigDecimal price;

    public CourseDTO(Course c) {
        this.id = c.getId();
        this.user = c.getUser();
        this.title = c.getTitle();
        this.description = c.getDescription();
        this.price = c.getPrice();
    }

}
