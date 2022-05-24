package com.ksga.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ksga.demo.model.CV;
import com.ksga.demo.service.CVServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cvs")
@RequiredArgsConstructor
public class CVRestController {

    private final CVServiceImp serviceImp;

    @GetMapping
    List<CV> findAll(){
        return serviceImp.findAll();
    }
    @PostMapping
   int add(@RequestBody CV cv) throws JSONException, JsonProcessingException {
        return serviceImp.add(cv);
    }
}
