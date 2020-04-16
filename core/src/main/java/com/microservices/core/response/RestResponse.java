package com.microservices.core.response;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class RestResponse {

    public String message;
    public String exception;
    public Integer total;
    public Set elements;
    public Object object;

    public static RestResponse ok(String message) {
        RestResponse r = new RestResponse();
        r.message = message;
        return r;
    }

    public static RestResponse ok(int total, Set elements) {
        RestResponse r = new RestResponse();
        r.total = total;
        r.elements = elements;
        return r;
    }

    public static RestResponse ok(int total, Object object) {
        RestResponse r = new RestResponse();
        r.total = total;
        r.object = object;
        return r;
    }

    public static RestResponse error(Exception e, String message) {
        RestResponse r = new RestResponse();
        log.error(e.getMessage(), e);
        r.exception = e.getMessage();
        r.message = message;
        return r;
    }
}
