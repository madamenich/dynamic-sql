package com.ksga.demo.supports.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ksga.demo.model.CustomSection;

import java.util.List;

public class JsonCustomSectionHandler extends  JsonTypeHandler<List<CustomSection>> {

    public JsonCustomSectionHandler() {
        super(new TypeReference<List<CustomSection>>(){

        });
    }
}


