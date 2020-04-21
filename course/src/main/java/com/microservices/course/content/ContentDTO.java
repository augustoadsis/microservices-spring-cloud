package com.microservices.course.content;


import lombok.*;

import java.io.Serializable;

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

}
