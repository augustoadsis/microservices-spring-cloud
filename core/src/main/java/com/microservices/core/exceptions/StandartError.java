package com.microservices.core.exceptions;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandartError {

    private Long timestamp;
    private Integer status;
    private String message;
    private List<String> errors = new ArrayList<>();
    private String trace;
    private String path;

}
