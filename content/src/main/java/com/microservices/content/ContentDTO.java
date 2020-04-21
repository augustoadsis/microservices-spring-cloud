package com.microservices.content;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO implements Serializable {

    private Long id;
    private Long course;

    private String title;
    private String url;

    public ContentDTO(Content c) {
        this.id = c.getId();
        this.course = c.getCourse();
        this.title = c.getTitle();
        this.url = c.getUrl();
    }

}
