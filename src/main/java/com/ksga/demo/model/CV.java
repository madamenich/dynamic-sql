package com.ksga.demo.model;

import lombok.*;

import java.util.List;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CV {
    private String uid;
    private String cvName;
    private List<CustomSection> experiences;
}
