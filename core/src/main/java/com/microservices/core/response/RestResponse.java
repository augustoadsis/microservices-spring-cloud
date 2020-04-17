package com.microservices.core.response;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RestResponse {

    private String message;
    public Object data;
    public List<Object> errors;
    public PageInfo pagination;
    public BigDecimal total;

    public ResponseEntity<Object> searchOk(Page page){
        RestResponse r = new RestResponse();
        r.message = "Search ok.";
        r.data = page.getContent();
        r.pagination = new PageInfo(page);
        return ResponseEntity.ok().body(r);
    }

    public ResponseEntity<Object> searchOk(Page page, BigDecimal total){
        RestResponse r = new RestResponse();
        r.message = "Search ok.";
        r.data = page.getContent();
        r.total = total;
        r.pagination = new PageInfo(page);
        return ResponseEntity.ok().body(r);
    }

    public ResponseEntity<Object> searchOk(Object data){
        RestResponse r = new RestResponse();
        r.message = "Search ok.";
        r.data = data;
        return ResponseEntity.ok().body(r);
    }

    public ResponseEntity<Object> searchOk(Object data, HttpHeaders headers){
        RestResponse r = new RestResponse();
        r.message = "Search ok.";
        r.data = data;
        return ResponseEntity.ok().headers(headers).body(r);
    }

    public ResponseEntity<Object> searchOk(Object data, String message) {
        RestResponse r = new RestResponse();
        r.message = message;
        r.data = data;
        return ResponseEntity.ok().body(r);
    }

    public ResponseEntity<Object> ok(Object data){
        RestResponse r = new RestResponse();
        r.message = "Saved";
        r.data = data;
        return ResponseEntity.ok().body(r);
    }

    public ResponseEntity<Object> ok(String message) {
        RestResponse r = new RestResponse();
        r.message = message;
        return ResponseEntity.ok().body(r);
    }

    public ResponseEntity<Object> badRequest(Object data) {
        RestResponse r = new RestResponse();
        r.message = "Error while processing.";
        r.data = data;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
    }

    public ResponseEntity<Object> badRequest(Object data, String message) {
        RestResponse r = new RestResponse();
        r.message = message;
        r.data = data;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
    }

    public ResponseEntity<Object> ok(Object data, String message){
        RestResponse r = new RestResponse();
        r.message = message;
        r.data = data;
        return ResponseEntity.ok().body(r);
    }


    public ResponseEntity<Object> error(String error) {
        RestResponse r = new RestResponse();
        r.message = "Fields not filled correctly, please verify and try again";
        r.errors = new ArrayList<>();
        r.errors = List.of(error);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(r);
    }

    public ResponseEntity<Object> badRequest(String error) {
        RestResponse r = new RestResponse();
        r.message = "Error while processing.";
        r.errors = new ArrayList<>();
        r.errors = List.of(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
    }

    public ResponseEntity<Object> ok(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Object> notFound(){
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> deleted(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    public ResponseEntity<Object> noContent(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
