package com.microservices.content;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("content")
public class Content {

    @Id
    @Column("id")
    private Long id;
    @Column("course")
    private Long course;
    @Column("title")
    private String title;
    @Column("url")
    private String url;
}
