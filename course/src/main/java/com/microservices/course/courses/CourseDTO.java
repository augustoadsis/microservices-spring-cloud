package com.microservices.course.courses;


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

    private String title;
    private String description;
    private BigDecimal price;

    public CourseDTO(Course c) {
        this.id = c.getId();
        this.title = c.getTitle();
        this.description = c.getDescription();
        this.price = c.getPrice();
    }

    public static Set<CourseDTO> fromSet(Set<Course> courses) {
        return courses.stream().map(c -> new CourseDTO(c)).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
