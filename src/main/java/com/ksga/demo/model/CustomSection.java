package com.ksga.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CustomSection {
    private  String title;
    private String  body;

    public CustomSection() {
        super();
    }
}
