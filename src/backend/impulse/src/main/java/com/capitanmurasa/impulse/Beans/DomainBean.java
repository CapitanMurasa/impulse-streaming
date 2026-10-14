package com.capitanmurasa.impulse.Beans;

import lombok.Data;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;


@Data
public class DomainBean {

    //String id;
    JsonResponseStatus ResponseStatus;
    String name;
    String data;

}