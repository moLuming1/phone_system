package com.sdau.controller;

import com.sdau.common.R;
import com.sdau.entity.Academy;
import com.sdau.service.AcademyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;


    @PostMapping
    public R<String> save(@RequestBody Academy academy){
        log.info("学院信息：{}",academy);
        academyService.save(academy);
        return R.success("新增学院系信息成功");
    }



}
