package com.microservices.subscription.course;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

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

}
