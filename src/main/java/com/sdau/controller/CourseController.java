package com.sdau.controller;

import com.sdau.service.CourseService;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;





}
