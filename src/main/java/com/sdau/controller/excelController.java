package com.sdau.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.sdau.common.R;
import com.sdau.dto.TeacherDto;
import com.sdau.entity.Student;
import com.sdau.entity.Teacher;
import com.sdau.listener.ReadListener;
import com.sdau.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("upload")
public class excelController {

    @Value("${phoneSystem.path}")  //从yml配置文件读取信息
    private String basePath;


    @Autowired
    private TeacherService teacherService;


    /**
     *    教师信息文件上传
     * 1. 编写excel中每一行对应的实体类
     * 2. 由于默认异步读取excel，所以需要逐行读取的回调监听器
     * 3. 开始读取Excel
     */
    @PostMapping("teaher")
    public R<String> upload(MultipartFile file) throws IOException {
        ExcelReaderBuilder workBook = EasyExcel.read(file.getInputStream(), TeacherDto.class, new ReadListener<TeacherDto>(teacherService));
        workBook.sheet().doRead();
        return R.success("上传成功");
    }




}