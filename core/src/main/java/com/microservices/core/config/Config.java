package com.microservices.core.config;

import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.util.Date;

@Configuration
public class Config {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

        Provider<Date> dateProvider = new AbstractProvider<Date>() {
            @Override
            public Date get() {
                return new Date();
            }
        };

        Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                try {
                    return DateUtils.parseDate(source, "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy");
                } catch (ParseException e) {
                    return new Date();
                }
            }
        };

        mapper.createTypeMap(String.class, Date.class);
        mapper.addConverter(toStringDate);

        mapper.getTypeMap(String.class, Date.class).setProvider(dateProvider);


        mapper.getConfiguration().setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        return mapper;
    }


}
