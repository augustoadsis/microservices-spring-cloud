package com.microservices.course;

import com.microservices.core.abstractEntity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends AbstractEntity {

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal price;

    private Long user;

}
