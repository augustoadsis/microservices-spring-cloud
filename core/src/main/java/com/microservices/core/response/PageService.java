package com.microservices.core.response;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class PageService {

    private static List<Object> objects = new ArrayList<>();

    public static PageRequest of(Integer page, Integer size, String direction, String orderBy, Object o){

        if(nonNull(o))
            verifyValidSortFields(o, orderBy);

        String d = "ASC";
        if(nonNull(direction)){
            if(direction.equalsIgnoreCase("ASC") || direction.equalsIgnoreCase("DESC")){
                d = direction.toUpperCase();
            }
        }
        return PageRequest.of(page, size, Sort.Direction.valueOf( d ), orderBy);
    }

    public static PageRequest ofNoValidate(Integer page, Integer size, String direction, String orderBy, Object o){
        String d = "ASC";
        if(nonNull(direction)){
            if(direction.equalsIgnoreCase("ASC") || direction.equalsIgnoreCase("DESC")){
                d = direction.toUpperCase();
            }
        }
        return PageRequest.of(page, size);
    }

    private static boolean verifyValidSortFields(Object o, String sortBy) {
        Class<?> mClass = o.getClass();
        Field[] fields = mClass.getDeclaredFields();

        for(Field f : fields){
            objects.add(f.getName());
        }
        if(!objects.contains(sortBy))
           throw new RuntimeException("[ " +sortBy + " ] is not part of object "+ mClass.getSimpleName().toUpperCase() +" to use \'orderBy\' of pagination, please use these fields [ "+ objects.stream().map(obj -> obj.toString()).collect(Collectors.joining(", ")) +" ], please verify and try again");
        return true;
    }
}
