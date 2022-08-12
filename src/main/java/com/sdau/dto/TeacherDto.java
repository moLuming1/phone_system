package com.sdau.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sdau.entity.Teacher;
import lombok.Data;

@Data
public class TeacherDto extends Teacher {

    @ExcelProperty("学院")
    private String dtoName;

    @ExcelProperty("系")
    private String system;
}
